<template>
  <div>
    <Form inline>
      <#list parameters as p>
      <#if typeProperties("iview-table-col-ignore",p.code)=='' && p.keyType?? && p.keyType gt 0>
      <FormItem label="${p.name}" prop="${p.javaName}">
        <#if p.type=='String'>
        <Input v-model="queryParam.${p.javaName}" />
        <#elseif p.type=='Integer' && p.keyType?? && p.keyType==4>
        <Select v-model="queryParam.${p.javaName}">
          <#list p.remark?split(',') as v>
          <Option :value="${v?split('-')[0]}" label="${v?split('-')[1]}"></Option>
        </#list>
        </Select>
        <#elseif p.type=='Integer' || p.type=='Long'>
        <Input v-model="queryParam.${p.javaName}" type="number" />
        <#elseif p.type=='Double'>
        <InputNumber v-model="queryParam.${p.javaName}" :step="0.1"></InputNumber>
        <#else>
        <Input v-model="queryParam.${p.javaName}" />
      </#if>
      </FormItem>
    </#if>
  </#list>
      <FormItem>
        <Button @click="showCreate">新增</Button>
      </FormItem>
    </Form>
    <Table :data="page.content" :columns="columns" :loading="loading">
      <template slot-scope="{row,index}" slot="operation">
        <Button type="text" @click="showEdit(row)">修改</Button>
        <Button type="text" @click="deleteRow(row,index)">删除</Button>
      </template>
    </Table>
    <Page :total="page.total" :page-size="page.limit" show-total show-sizer @on-change="fetchData" @on-page-size-change="changeSize"></Page>
    <FormModal :value="editValue" :show="showEditModal" @hide="showEditModal=false"></FormModal>
  </div>
</template>
<script>
import FormModal from './Form.vue'

export default {
  components: {
    FormModal
  },
  props:{
      <#list parameters as p>
        <#if p.keyType?? && p.keyType == 2>
        ${p.javaName}:{
         required: true
        }
        </#if>
      </#list>
  },
  data () {
    const vm = this
    return {
      columns: [
      <#list parameters as p>
        <#if typeProperties("iview-table-col-ignore",p.code) == ''>
        {
          key: '${p.javaName}',
          title: '${p.name}' <#if p.type == 'Integer' && p.keyType?? && p.keyType == 4>,
          render: function(h,params){
            const map = {
          <#list p.remark?split(',') as v>
              ${v?split('-')[0]}: '${v?split('-')[1]}'<#if v_has_next>,</#if>
          </#list>
            }
            return h('span',map[params.row.${p.javaName}])
          }
          </#if>
         },
        </#if>
      </#list>
        { title: '操作', slot: 'operation'}
      ],
      size: 10,
      queryParam:{
      <#list parameters as p>
      <#if p.keyType??>
      <#if p.keyType == 2>
        ${p.javaName}:vm.${p.javaName},
      </#if>
      <#if p.keyType == 3>
        ${p.javaName}:null,
      </#if>
      </#if>
      </#list>
      },
      loading: false,
      page: {},
      showEditModal:false,
      editValue:{}
    }
  },
  created () {
    this.fetchData(1)
  },
  methods: {
    fetchData (number) {
      const vm = this
      this.loading = true
      this.$axios.post('${app.code}/${model.javaName}/page?number='+number+'&size='+vm.size,vm.queryParam)
        .then(data => {
          vm.page = Object.assign({},data)
          vm.loading = false
        })
    },
    changeSize (size){
      this.size = size
      this.fetchData(1)
    },
    showCreate () {
        this.editValue = {}
        this.showEditModal = true
    },
    showEdit (row) {
        this.editValue = Object.assign({},row)
        this.showEditModal = true
    },
    deleteRow(row,index){

    }
  }
}
</script>

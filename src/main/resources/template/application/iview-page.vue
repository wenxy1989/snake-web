<template>
  <div>
    <Form inline>
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
    <Page :total="page.total" :page-size="page.limit" show-sizer @on-change="fetchData" @on-page-size-change="changeSize"></Page>
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
      <#list parameters as obj>
        <#if obj.keyType?? && obj.keyType == 2>
        ${obj.code}:{
         required: true
        }
        </#if>
      </#list>
  },
  data () {
    const vm = this
    return {
      columns: [
      <#list parameters as obj>
        <#if typeProperties("iview-table-col-ignore",obj.code) == ''>
        {
          key: '${obj.code}',
          title: '${obj.name}' <#if obj.type == 'Integer' && obj.keyType?? && obj.keyType == 4>,
          render: function(h,params){
            const map = {
          <#list obj.remark?split(',') as v>
              ${v?split('-')[0]}: '${v?split('-')[1]}'<#if v_has_next>,</#if>
          </#list>
            }
            return h('span',map[params.row.${obj.code}])
          }
          </#if>
         },
        </#if>
      </#list>
        { title: '操作', slot: 'operation'}
      ],
      size: 10,
      queryParam:{
      <#list parameters as obj>
      <#if obj.keyType??>
      <#if obj.keyType == 2>
        ${obj.code}:vm.${obj.code},
      </#if>
      <#if obj.keyType == 3>
        ${obj.code}:null,
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
      this.$axios.post('${app.code}/${model.code}/page?number='+number+'&size='+vm.size,vm.queryParam)
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

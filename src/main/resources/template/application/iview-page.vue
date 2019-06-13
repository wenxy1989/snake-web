<template>
  <div>
    <Button @click="showCreate">新增</Button>
    <Table :data="page.content" :columns="columns">
      <template slot-scope="{row,index}" slot="operation">
        <Button type="text" @click="showEdit(row)">修改</Button>
        <Button type="text" @click="deleteRow(row,index)">删除</Button>
      </template>
    </Table>
    <Page :total="page.total" :page-size="page.limit" show-sizer @onchange="fetchData" @on-page-size-change="changeSize"></Page>
    <FormModal :value="editValue" :show="showEditModal"></FormModal>
  </div>
</template>
<script>
import FormModal from './Form.vue'

export default {
  components: {
    FormModal
  },
  data () {
    return {
      columns: [
      <#list parameters as obj>
        <#if typeProperties("iview-table-col-ignore",obj.code) == ''>
        { key: '${obj.code}', title: '${obj.name}' },
        </#if>
      </#list>
        { title:'操作',slot:'operation'}
      ],
      size: 10,
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
      this.$axios.get('${app.code}/${model.code}/page?number='+number+'&size='+vm.size)
        .then(res => {
          vm.page = Object.assign({},res.data.data)
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

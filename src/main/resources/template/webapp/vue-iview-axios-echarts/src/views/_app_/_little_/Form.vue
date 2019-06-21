<template>
  <Modal v-model="showModal" title="${model.name}" ok-text="保存" @on-ok="saveData" @on-cancel="cancelSave">
    <Form ref="dataForm" :model="value" :rules="rules" :label-width="80">
      <#list parameters as p>
      <#if typeProperties("iview-table-col-ignore",p.code)==''>
      <FormItem label="${p.name}" prop="${p.javaName}">
        <#if p.type=='String'>
        <Input v-model="value.${p.javaName}" />
        <#elseif p.type=='Integer' && p.keyType?? && p.keyType==4>
        <Select v-model="value.${p.javaName}">
          <#list p.remark?split(',') as v>
          <Option :value="${v?split('-')[0]}" label="${v?split('-')[1]}"></Option>
        </#list>
        </Select>
        <#elseif p.type=='Integer' || p.type=='Long'>
        <Input v-model="value.${p.javaName}" type="number" />
        <#elseif p.type=='Double'>
        <InputNumber v-model="value.${p.javaName}" :step="0.1"></InputNumber>
        <#else>
        <Input v-model="value.${p.javaName}" />
      </#if>
      </FormItem>
    </#if>
  </#list>
  </Form>
  </Modal>
</template>
<script>
export default {
  props: {
    show: {
      type: Boolean,
      required: true
    },
    value: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      showModal: false,
      rules: {}
    }
  },
  watch: {
    show (nv, ov) {
      this.showModal = nv
    }
  },
  methods: {
    saveData () {
      const vm = this
      this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const uri = vm.data.id ? '/${app.code}/${model.javaName}/update' : '/${app.code}/${model.javaName}/create'
            vm.$axios.post(uri, vm.value)
            .then(data => {
              vm.$emit('hide')
              vm.$Message.success({
                duration: 5,
                content: '操作成功'
              })
            })
            .catch( e => {
              vm.$emit('hide')
            })
          }
      })
    },
    cancelSave () {
      this.$emit('hide')
    }
  }
}
</script>

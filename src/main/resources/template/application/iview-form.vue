<template>
    <Modal v-model="showModal" title="${model.name}" ok-text="保存" @on-ok="saveData" @on-cancel="cancelSave">
        <Form ref="dataForm" :model="value" :rules="rules" :label-width="80">
            <#list parameters as obj>
            <#if typeProperties("iview-table-col-ignore",obj.code) == ''>
            <FormItem label="${obj.name}" prop="${obj.code}">
            <#if obj.type == 'String'>
                <Input v-model="value.${obj.code}" />
            <#elseif obj.type == 'Integer' && obj.keyType?? && obj.keyType == 4>
                <Select v-model="value.${obj.code}">
                    <#list obj.remark?split(',') as v>
                    <Option :value="${v?split('-')[0]}" label="${v?split('-')[1]}"></Option>
                    </#list>
                </Select>
            <#elseif obj.type == 'Integer' || obj.type == 'Long'>
                <Input v-model="value.${obj.code}" type="number" />
            <#elseif obj.type == 'Double'>
                <InputNumber v-model="value.${obj.code}" :step="0.1"></InputNumber>
            <#else>
                <Input v-model="value.${obj.code}" />
            </#if>
            </FormItem>
            </#if>
            </#list>
        </Form>
    </Modal>
</template>
<script>
    export default {
        props:{
            show:{
                type:Boolean,
                required:true
            },
            value:{
                type:Object,
                required:true
            }
        },
        data() {
            return {
                showModal: false,
                rules: {}
            }
        },
        watch:{
            show(nv,ov){
                this.showModal = nv
            }
        },
        methods: {
            saveData() {
                const vm = this
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        const uri = vm.data.id ? 'update' : 'create'
                        vm.$axios.post(uri, vm.value)
                            .then(data => {
                                vm.$Message.success({
                                    duration: 5,
                                    content: '操作成功'
                                })
                            })
                    }
                })
            },
            cancelSave(){
                this.$emit('hide')
            }
        }
    }
</script>

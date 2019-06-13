<template>
    <Modal v-model="show" title="${model.name}" ok-text="保存" @on-ok="saveData" @on-cancel="cancelSave">
        <Form ref="dataForm" :model="value" :rules="rules" :label-width="80">
            <#list parameters as obj>
            <#if typeProperties("iview-table-col-ignore",obj.code) == ''>
            <FormItem label="${obj.name}" prop="${obj.code}">
                <Input v-model="value.${obj.code}"></Input>
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
                rules: {}
            }
        },
        methods: {
            saveData() {
                const vm = this
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        const uri = vm.data.id ? 'update' : 'create'
                        vm.$axios.post(uri, vm.value)
                            .then(res => {
                                vm.$Message.success({
                                    duration: 5,
                                    content: res.data.message
                                })
                            })
                    }
                })
            },
            cancelSave(){
                this.show = false
            }
        }
    }
</script>

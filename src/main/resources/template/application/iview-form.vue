<template>
    <Modal v-model="show" title="${model.name}" ok-text="保存" @on-ok="saveData" @on-cancel="cancelSave">
        <Form ref="dataForm" :model="data" :rules="rules" :label-width="80">
            <#list parameters as obj>
            <FormItem label="${obj.name}" prop="${obj.code}">
                <Input v-model="data.${obj.code}"></Input>
            </FormItem>
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
            }
        },
        data() {
            return {
                data: {},
                rules: {}
            }
        },
        methods: {
            saveData() {
                const vm = this
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        const uri = vm.data.id ? 'update' : 'create'
                        vm.$axios.post(uri, vm.data)
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

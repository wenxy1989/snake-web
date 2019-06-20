export default [
    <#list modelList as model>
{
    name: '${model.name}',
    path: '/${app.code}-${model.javaName}',
    component: resolve => require(['@/views/${model.javaName}/Page.vue'], resolve)
}<#if model_has_next>,</#if>
    </#list>
  ]
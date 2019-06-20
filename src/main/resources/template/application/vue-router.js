import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
export default new VueRouter({
  mode: 'hash',
  routes: [{
      path: '/',
      redirect: '/${app.code}-${modelList?first.code}'
    <#list modelList as model>
    },
    {
      name: '${model.name}',
      path: '/${app.code}-${model.javaName}',
      component: resolve => require(['@/views/${model.javaName}/Page.vue'], resolve)
    </#list>
    }
  ]
})
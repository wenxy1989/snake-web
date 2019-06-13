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
      path: '/${app.code}-${model.code}',
      component: resolve => require(['@/views/${model.code}/Page.vue'], resolve)
    </#list>
    }
  ]
})
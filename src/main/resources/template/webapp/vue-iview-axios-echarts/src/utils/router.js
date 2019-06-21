import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './routes'

Vue.use(VueRouter)
export default new VueRouter({
  mode: 'hash',
  routes: [{
      path: '/',
      redirect: '/login'
    },
    {
      name: '${app.name}-登录',
      path: '/login',
      component: resolve => require(['@/views/Login.vue'], resolve)
    },
    {
      name: '${app.name}-管理',
      path: '/main',
      component: resolve => require(['@/views/Main.vue'], resolve),
      children: [{
          name: '用户信息',
          path: '/',
          redirect: '/system-user-6',
        },
        ...routes
      ]
    }
  ]
})

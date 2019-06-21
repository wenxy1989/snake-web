import Vue from 'vue'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import axios from './utils/axios'
import router from './utils/router'
import App from './views/App.vue'

Vue.use(iView)
Vue.prototype.$axios = axios

new Vue({
  el: '#app',
  render: h => h(App),
  router
})
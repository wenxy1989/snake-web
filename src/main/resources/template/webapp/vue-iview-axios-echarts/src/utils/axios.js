import axios from 'axios'
import {
  Modal,
  Spin
} from 'iview'

const instance = axios.create({
  baseURL: 'http://localhost:8000/'
})

const showSpin = () => {
  Spin.show({
    render: (h) => {
      return h('div', [
        h('Icon', {
          'class': 'demo-spin-icon-load',
          props: {
            type: 'ios-loading',
            size: 18
          }
        }),
        h('div', 'Loading')
      ])
    }
  });
}
const hideSpin = () => {
  Spin.hide()
}
instance.interceptors.request.use(config => {
  showSpin()
  const token = localStorage.getItem('AuthorizationToken')
  if (token) {
    config.headers.Authorization = token
  }
  return config
}, error => {
  hideSpin()
  return Promise.reject(error)
})

instance.interceptors.response.use(response => {
  hideSpin()
  if (response.data && response.data.code != undefined) {
    if (response.data.code === 0) {
      return Promise.resolve(response.data.data)
    } else {
      Modal.error({
        title: '错误提示',
        content: response.data.message
      })
      return Promise.reject(response.data)
    }
  }
  return Promise.reject({
    code: 4,
    message: 'response return null'
  })
}, error => {
  hideSpin()
  return Promise.reject(error)
})

export default instance
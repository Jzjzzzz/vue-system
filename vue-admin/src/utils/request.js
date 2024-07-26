import axios from 'axios'
import {Loading, Message, MessageBox} from 'element-ui'
import store from '@/store'
import {getToken} from '@/utils/auth'
import {blobValidate, tansParams} from "@/utils/vblog";
import { saveAs } from 'file-saver'
import errorCode from "@/utils/errorCode";
let downloadLoadingInstance;
// 创建一个axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // 跨域请求时发送cookies
  timeout: 5000 // 请求超时
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 在发送请求之前做一些事情
    if (store.getters.token) {
      // 让每个请求都携带token
      config.headers['token'] = getToken()
    }
    // get请求映射params参数
    if (config.method === 'get' && config.params) {
      let url = config.url + '?' + tansParams(config.params);
      url = url.slice(0, -1);
      config.params = {};
      config.url = url;
    }
    return config
  },
  error => {
    // 处理请求错误
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  /**
   * 如果你想获取http信息，比如headers或者status
   * 请返回响应 => 响应
   */

  /**
   * 通过自定义代码判断请求状态
   * 这里只是一个例子
   * 也可以通过 HTTP Status Code 来判断状态
   */
  response => {
    const res = response.data
    // 如果响应类型是文件流，直接返回响应
    // 二进制数据则直接返回
    if (response.request.responseType ===  'blob' || response.request.responseType ===  'arraybuffer') {
      return response
    }
    // 如果自定义代码不是20000，则判断为错误。
    if (res.code !== 20000) {
      Message({
        message: res.msg || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 50008：非法令牌； 50012：其他客户端登录； 50014：令牌过期；
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('您已登出，您可以取消留在此页面，或重新登入', '确认注销', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.msg,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

// 通用下载方法
export function download(url, params, filename, config) {
  downloadLoadingInstance = Loading.service({ text: "正在下载数据，请稍候", spinner: "el-icon-loading", background: "rgba(0, 0, 0, 0.7)", })
  return service.post(url, params, {
    transformRequest: [(params) => { return tansParams(params) }],
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    responseType: 'blob',
    ...config
  }).then(async (res) => {
    const isBlob = blobValidate(res.data);
    if (isBlob) {
      const blob = new Blob([res.data])
      saveAs(blob, filename)
    } else {
      const resText = await res.data.text();
      const rspObj = JSON.parse(resText);
      const errMsg = errorCode[rspObj.code] || rspObj.msg || errorCode['default']
      Message.error(errMsg);
    }
    downloadLoadingInstance.close();
  }).catch((r) => {
    console.error(r)
    Message.error('下载文件出现错误，请联系管理员！')
    downloadLoadingInstance.close();
  })
}

export default service

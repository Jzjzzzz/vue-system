import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/styles/index.scss' // global css
import '@/icons' // icon
import '@/permission' // permission control
import './assets/styles/element-variables.scss'
import '@/assets/styles/index.scss' // global css
import '@/assets/styles/vblog.scss' // vblog css
import 'mavon-editor/dist/css/index.css'
import './assets/fonts/index.css'

import App from './App'
import store from './store'
import router from './router'

import {
  addDateRange,
  handleTree,
  isPerList,
  parseTime,
  resetForm,
  selectDictLabel,
  selectDictLabels
} from '@/utils/vblog'
import {getDicts} from '@/api/system/dict/data'
import {download} from '@/utils/request'

import plugins from './plugins' // plugins
// 分页组件
import Pagination from '@/components/Pagination'
// 字典数据组件
import DictData from '@/components/DictData'
// 字典标签组件
import DictTag from '@/components/DictTag'
// 自定义表格工具组件
import RightToolbar from '@/components/RightToolbar'
// 富文本组件
import Editor from '@/components/Editor'
// 普通图片上传组件
import EleUploadImage from 'vue-ele-upload-image'
import VueSocketIO from 'vue-socket.io'
import socketIo from 'socket.io-client'
// Markdown
import mavonEditor from 'mavon-editor'

import FcDesigner from '@form-create/designer'
import formCreate from '@form-create/element-ui'

// 自定义权限判断方法
import hasBtnPermission from '@/utils/btn-permission'
import directive from './directive'
import {getToken} from "@/utils/auth"; // directive

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const {mockXHR} = require('../mock')
  mockXHR()
}

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.addDateRange = addDateRange
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.isPreList = isPerList
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.handleTree = handleTree
Vue.prototype.$hasBP = hasBtnPermission
Vue.prototype.download = download
Vue.component('Editor', Editor)
Vue.component(EleUploadImage.name, EleUploadImage)
Vue.component('DictTag', DictTag)
Vue.component('Pagination', Pagination)
Vue.component('RightToolbar', RightToolbar)

// set ElementUI lang to EN
// Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
Vue.use(ElementUI)
Vue.use(plugins)
Vue.use(mavonEditor)
Vue.use(directive)
Vue.use(FcDesigner)
Vue.use(formCreate)
Vue.use(new VueSocketIO({
  debug: true,
  connection: socketIo('http://127.0.0.1:9999?token=' + getToken()),
  transports: ['websocket'],
  extraHeaders: {
    'Access-Control-Allow-Origin': '*' // 设置跨域请求头
  }
}))
DictData.install()

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})

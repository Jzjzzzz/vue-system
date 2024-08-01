import request from '@/utils/request'

// 查询审批模板列表
export function listTemplate(query) {
  return request({
    url: '/oa/template/list',
    method: 'get',
    params: query
  })
}

// 查询审批模板详细
export function getTemplate(id) {
  return request({
    url: '/oa/template/' + id,
    method: 'get'
  })
}

// 新增审批模板
export function addTemplate(data) {
  return request({
    url: '/oa/template',
    method: 'post',
    data: data
  })
}

// 修改审批模板
export function updateTemplate(data) {
  return request({
    url: '/oa/template',
    method: 'put',
    data: data
  })
}

// 删除审批模板
export function delTemplate(id) {
  return request({
    url: '/oa/template/' + id,
    method: 'delete'
  })
}

//发布
export function publish(id) {
  return request({
    url: `/oa/template/${id}`,
    method: 'get'
  })
}

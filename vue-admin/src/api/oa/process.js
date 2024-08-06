import request from '@/utils/request'

// 查询审批类型列表
export function listProcess(query) {
  return request({
    url: `/oa/process/list`,
    method: 'get',
    params: query
  })
}

// 查询审批类型详细
export function getProcess(id) {
  return request({
    url: `/oa/process/${id}`,
    method: 'get'
  })
}

// 修改审批类型
export function updateProcess(data) {
  return request({
    url: `/oa/process`,
    method: 'put',
    data: data
  })
}

// 删除审批类型
export function delProcess(id) {
  return request({
    url: `/oa/process/${id}`,
    method: 'delete'
  })
}
// 获取全部审批分类及模板
export function findProcessType(){
  return request({
    url: `/oa/process/findProcessType`,
    method: 'get'
  })
}
// 启动流程
export function startUp(data){
  return request({
    url: `/oa/process/startUp`,
    method: 'post',
    data: data
  })
}

export function show(id){
  return request({
    url: `/oa/process/show/${id}`,
    method: 'get'
  })
}

export function find(query){
  return request({
    url: `/oa/process/find`,
    method: 'get',
    params: query
  })
}

export function approve(approvalVo){
  return request({
    url: `/oa/process/approve`,
    method: 'post',
    data: approvalVo
  })
}

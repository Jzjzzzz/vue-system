import request from '@/utils/request'

// 查询i茅台执行日志列表
export function listLog(query) {
  return request({
    url: `/mt/log/list`,
    method: 'get',
    params: query
  })
}

// 查询i茅台执行日志详细
export function getLog(id) {
  return request({
    url: `/mt/log/${id}`,
    method: 'get'
  })
}

// 新增i茅台执行日志
export function addLog(data) {
  return request({
    url: `/mt/log`,
    method: 'post',
    data: data
  })
}

// 修改i茅台执行日志
export function updateLog(data) {
  return request({
    url: `/mt/log`,
    method: 'put',
    data: data
  })
}

// 删除i茅台执行日志
export function delLog(id) {
  return request({
    url: `/mt/log/${id}`,
    method: 'delete'
  })
}

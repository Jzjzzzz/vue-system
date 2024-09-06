import request from '@/utils/request'

// 查询i茅台用户列表
export function listUser(query) {
  return request({
    url: `/mt/user/list`,
    method: 'get',
    params: query
  })
}

// 查询i茅台用户详细
export function getUser(id) {
  return request({
    url: `/mt/user/${id}`,
    method: 'get'
  })
}

// 新增i茅台用户
export function addUser(data) {
  return request({
    url: `/mt/user`,
    method: 'post',
    data: data
  })
}

// 修改i茅台用户
export function updateUser(data) {
  return request({
    url: `/mt/user`,
    method: 'put',
    data: data
  })
}

// 删除i茅台用户
export function delUser(id) {
  return request({
    url: `/mt/user/${id}`,
    method: 'delete'
  })
}

// 发送验证码
export function sendCode(mobile, deviceId) {
  return request({
    url: '/mt/user/sendCode',
    method: 'get',
    params: {mobile: mobile, deviceId: deviceId}
  })
}

// 查询i茅台用户列表
export function login(mobile, code, deviceId) {
  return request({
    url: '/mt/user/login',
    method: 'get',
    params: {mobile: mobile, code: code, deviceId: deviceId}
  })
}

// reservation
export function reservation(mobile) {
  return request({
    url: '/mt/user/reservation',
    method: 'get',
    params: {mobile}
  })
}

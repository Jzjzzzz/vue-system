import request from '@/utils/request'

export function login(data) {
  return request({
    url: `/admin/login`,
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: `/admin/login/info`,
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: `/admin/login/logout`,
    method: 'post'
  })
}

// 修改资料
export function updateUser(data) {
  return request({
    url: `/admin/login`,
    method: 'put',
    data: data
  })
}

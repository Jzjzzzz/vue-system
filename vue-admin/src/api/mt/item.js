import request from '@/utils/request'

// 查询I茅台预约商品列表
export function listItem(query) {
  return request({
    url: `/mt/item/list`,
    method: 'get',
    params: query
  })
}

// 查询I茅台预约商品详细
export function getItem(id) {
  return request({
    url: `/mt/item/${id}`,
    method: 'get'
  })
}

// 新增I茅台预约商品
export function addItem() {
  return request({
    url: `/mt/item`,
    method: 'post'
  })
}

// 查询I茅台预约商品列列表
export function itemAll() {
  return request({
    url: '/mt/item/listAll',
    method: 'get'
  })
}

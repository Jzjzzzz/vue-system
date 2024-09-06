import request from '@/utils/request'

// 查询i茅台商店列表
export function listShop(query) {
  return request({
    url: `/mt/shop/list`,
    method: 'get',
    params: query
  })
}

// 查询i茅台商店详细
export function getShop(id) {
  return request({
    url: `/mt/shop/${id}`,
    method: 'get'
  })
}

// 新增i茅台商店
export function addShop() {
  return request({
    url: `/mt/shop`,
    method: 'post'
  })
}


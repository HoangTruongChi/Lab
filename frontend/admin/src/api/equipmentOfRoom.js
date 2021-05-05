import request from '@/utils/request'

export function getEquipments(roomId) {
  return request({
    url: '/lab/equipmentOfRoom/fetch',
    method: 'get',
    params: {
      roomId: roomId
    }
  })
}

export function deleteEquipment(id) {
  return request({
    url: '/lab/equipmentOfRoom/delete',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function createEquipment(roomId, data) {
  return request({
    url: '/lab/equipmentOfRoom/add',
    method: 'post',
    params: {
      roomId: roomId
    },
    data
  })
}

export function updateEquipment(roomId, data) {
  return request({
    url: '/lab/equipmentOfRoom/update',
    method: 'put',
    params: {
      roomId: roomId
    },
    data
  })
}

export function listEquipments() {
  return request({
    url: '/lab/equipment/fetch',
    method: 'get'
  })
}

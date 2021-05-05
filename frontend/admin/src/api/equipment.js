import request from '@/utils/request'

export function getEquipments(roomId) {
  return request({
    url: '/lab/equipment/fetch',
    method: 'get',
    params: {
      roomId: roomId
    }
  })
}

export function deleteEquipment(equipmentId) {
  return request({
    url: '/lab/equipment/delete',
    method: 'delete',
    params: {
      equipmentId: equipmentId
    }
  })
}

export function createEquipment(data) {
  return request({
    url: '/lab/equipment/create',
    method: 'post',
    data
  })
}

export function updateEquipment(equipmentId, data) {
  return request({
    url: '/lab/equipment/update',
    method: 'put',
    params: {
      equipmentId: equipmentId
    },
    data
  })
}

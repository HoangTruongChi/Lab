import request from '@/utils/request'

export function fetchRooms() {
  return request({
    url: '/lab/room/fetch',
    method: 'get'
    /* params: query*/
  })
}

export function deleteRoom(roomId) {
  return request({
    url: '/lab/room/delete',
    method: 'delete',
    params: {
      roomId: roomId
    }
  })
}

export function createRoom(data) {
  return request({
    url: '/lab/room/create',
    method: 'post',
    data
  })
}

export function updateRoom(roomId, data) {
  return request({
    url: '/lab/room/update',
    method: 'put',
    params: {
      roomId: roomId
    },
    data
  })
}

export function getEquipments(roomId) {
  return request({
    url: '/lab/equipment/fetch',
    method: 'get',
    params: {
      roomId: roomId
    }
  })
}

export function addEquipments(roomId, data) {
  return request({
    url: '/lab/room/addEquipment',
    method: 'get',
    params: {
      roomId: roomId
    },
    data
  })
}

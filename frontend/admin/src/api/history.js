import request from '@/utils/request'

export function fetchRoomDivisions(date) {
  return request({
    url: '/lab/roomDivision/fetch',
    method: 'get',
    params: {
      date: date
    }
  })
}

export function getEquipmentDivisions(roomRegisId) {
  return request({
    url: '/lab/equipmentDivision/fetch',
    method: 'get',
    params: {
      roomRegisId: roomRegisId
    }
  })
}

export function getUser(roomRegisId) {
  return request({
    url: '/lab/userOfRoom/fetch',
    method: 'get',
    params: {
      roomRegisId: roomRegisId
    }
  })
}

export function getRooms() {
  return request({
    url: '/lab/room/fetch',
    method: 'get'
  })
}

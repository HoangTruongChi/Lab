import request from '../utils/request'

export function getEquipments(){
    return request({
        url: '/lab/equipment/fetch',
        method: 'get'
    })
}
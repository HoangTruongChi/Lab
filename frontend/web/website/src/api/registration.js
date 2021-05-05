import request from '../utils/request'

export function register(data){
    return request({
        url: '/lab/registration',
        headers: {'123': '2323'},
        method: 'post',
        data
    })
}
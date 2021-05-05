import request from '@/utils/request'

export function getUsers(){
  return request({
    url: '/auth/users',
    method: 'post'
  })
}

export function resetPass(username, password){
  return request({
    url: '/auth/user/reset_pass',
    method: 'post',
    data:{
      username: username,
      password: password
    }
  })
}

export function deleteUser(username, deleted){
  return request({
    url: '/auth/users',
    method: 'delete',
    data: {
      username: username,
      deleted: deleted
    }
  })
}

export function changePass(data){
  return request({
    url: '/auth/user/change_pass',
    method: 'post',
    data
  })
}

export function login(data) {
  console.log(data)
  return request({
    url: '/auth/signIn',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/auth/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-element-admin/user/logout',
    method: 'post'
  })
}

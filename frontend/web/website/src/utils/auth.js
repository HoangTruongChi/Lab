import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'
const roleKey = 'Role-Type'
const userKey = "Username"

export function remove(){
  removeToken()
  removeRole()
  removeUsername()
}

export function getUsername(){
  return Cookies.get(userKey)
}

export function setUsername(username){
  return Cookies.set(userKey, username)
}

export function removeUsername(){
  return Cookies.remove(userKey)
}

export function getRole(){
  return Cookies.get(roleKey)
}

export function setRole(role){
  return Cookies.set(roleKey, role)
}

export function removeRole(){
  return Cookies.remove(roleKey)
}

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

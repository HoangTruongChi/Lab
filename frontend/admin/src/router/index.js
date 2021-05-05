import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */
import componentsRouter from './modules/components'

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    hidden: true,
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'Dashboard', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/index',
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        name: 'Profile',
        meta: { title: 'Profile', icon: 'user', noCache: true }
      }
    ]
  },
  {
    path: '/categories',
    component: Layout,
    redirect: '/categories/Room',
    hidden: true,
    children: [
      {
        path: 'Room',
        component: () => import('@/views/categories/Room'),
        name: 'Room',
        meta: { title: 'Room', icon: 'user', noCache: true }
      }
    ]
  },
  {
    path: '/categories',
    component: Layout,
    redirect: '/categories/Equipment',
    hidden: true,
    children: [
      {
        path: 'Equipment',
        component: () => import('@/views/categories/Equipment'),
        name: 'Equipment',
        meta: { title: 'Equipment', icon: 'user', noCache: true }
      }
    ]
  },
  {
    path: '/categories',
    component: Layout,
    redirect: '/categories/EquipmentOfRoom',
    hidden: true,
    children: [
      {
        path: 'EquipmentOfRoom/:rid',
        component: () => import('@/views/categories/EquipmentOfRoom'),
        name: 'EquipmentOfRoom',
        meta: { title: 'EquipmentOfRoom', icon: 'user', noCache: true },
        props: true
      }
    ],
    props: true
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [

  componentsRouter,
  {
    path: '/reports',
    component: Layout,
    redirect: '/registration',
    name: 'Registration',
    meta: {
      title: 'Registration',
      icon: 'excel'
    },
    children: [
      {
        path: 'history',
        component: () => import('@/views/registration/History'),
        name: 'History',
        meta: { title: 'History' }
      }/*,
      {
        path: 'information',
        component: () => import('@/views/categories/Room'),
        name: 'Information',
        meta: { title: 'Information' }
      },

      {
        path: 'rate',
        component: () => import('@/views/categories/Room'),
        name: 'Rate',
        meta: { title: 'Rate' }
      }*/
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router

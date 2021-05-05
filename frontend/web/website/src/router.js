import Vue from "vue";
import Router from "vue-router";
import AppHeader from "./layout/AppHeader";
import AppFooter from "./layout/AppFooter";
import AppHeaderLogin from "./layout/AppHeaderLogin";
import Landing from "./views/Landing.vue";
import Login from "./views/Login.vue";
import Register from "./views/Register.vue";
import Registration from "./views/Registration.vue";
import ViewRegistration from "./views/ViewRegistration.vue"
import RegistrationInfo from "./views/RegistrationInfo.vue"
import RoomInfo from "./views/RoomInfo.vue"
import ChangeRegistration from "./views/ChangeRegistration.vue"
import Modal from "./views/Modal.vue"


import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from './utils/auth' // get token from cookie

NProgress.configure({ showSpinner: false }) // NProgress Configuration

Vue.use(Router);

const router = new Router({
  linkExactActiveClass: "active",
  routes: [
    // {
    //   path: "/",
    //   name: "components",
    //   components: {
    //     header: AppHeader,
    //     default: Components,
    //     footer: AppFooter
    //   }
    // },
    {
      path: "/",
      name: "landing",
      components: {
        header: AppHeaderLogin,
        default: Landing,
        footer: AppFooter
      }
    },
    {
      path: "/login",
      name: "login",
      components: {
        header: AppHeaderLogin,
        default: Login,
        footer: AppFooter
      }
    },
    {
      path: "/register",
      name: "register",
      components: {
        header: AppHeader,
        default: Register,
        footer: AppFooter
      }
    },
    {
      path: "/registration",
      name: "registration",
      components: {
        header: AppHeader,
        default: Registration,
        footer: AppFooter
      }
    },
    {
      path: "/view-registration",
      name: "View",
      components: {
        header: AppHeader,
        default: ViewRegistration,
        footer: AppFooter
      }
    },
    {
      path: "/registration-info",
      name: "regisInfo",
      components: {
        header: AppHeader,
        default: RegistrationInfo,
        footer: AppFooter
      }
    },
    {
      path: "/room-info",
      name: "roomInfo",
      components: {
        header: AppHeader,
        default: RoomInfo,
        footer: AppFooter
      }
    },
    {
      path: "/change-registration",
      name: "change",
      components: {
        header: AppHeader,
        default: ChangeRegistration,
        footer: AppFooter
      }
    },
    {
      path: "/modal",
      name: "modal",
      components: {
        header: AppHeader,
        default: Modal,
        footer: AppFooter
      }
    },
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    } 
  }
}); 

const whiteList = ['/login', '/', '/view-registration', '/registration-info', '/room-info', '/change-registration','/modal'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // determine whether the user has logged in
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/registration' })
      NProgress.done() // hack: https://github.com/PanJiaChen/vue-element-admin/pull/2939
    } else {

      next({ to, replace: true })

    }
  } else {
    /* has no token*/

    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})


export default router;
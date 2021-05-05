import Vue from "vue";
import Router from "vue-router";
import Header from "./layout/starter/StarterHeader";
import Footer from "./layout/starter/StarterFooter";
import Starter from "./views/Starter.vue";
import AppHeader from "@/layout/AppHeader";
import Landing from "@/views/Landing";
import AppFooter from "@/layout/AppFooter";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/landing",
      name: "landing",
      components: {
        header: AppHeader,
        default: Landing,
        footer: AppFooter
      }
    }
  ]
});

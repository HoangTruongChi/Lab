import Vue from "vue";
import Vuex from 'vuex';
import store from './store'
import App from "./App.vue";
import router from "./router";
import Argon from "./plugins/argon-kit";
import './registerServiceWorker'

Vue.config.productionTip = false;
Vue.use(Argon);
Vue.use(Vuex)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");

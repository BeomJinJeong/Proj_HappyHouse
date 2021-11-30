/*!

 =========================================================
 * Vue Light Bootstrap Dashboard - v2.0.0 (Bootstrap 4)
 =========================================================

 * Product Page: http://www.creative-tim.com/product/light-bootstrap-dashboard
 * Copyright 2019 Creative Tim (http://www.creative-tim.com)
 * Licensed under MIT (https://github.com/creativetimofficial/light-bootstrap-dashboard/blob/master/LICENSE.md)

 =========================================================

 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 */
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'

// LightBootstrap plugin
import LightBootstrap from './light-bootstrap-main'

// router setup
import routes from './routes/routes'

import './registerServiceWorker'

import store from "./store";
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios';
import http from '@/util/http-common.js';
import vueClapButton from 'vue-clap-button'
import VueSplide from '@splidejs/vue-splide';
import '@splidejs/splide/dist/css/themes/splide-default.min.css';
import VueKinesis from 'vue-kinesis';

// plugin setup
Vue.use(VueRouter)

Vue.use(BootstrapVue)
Vue.use(LightBootstrap)

// clapAnimationBtn
Vue.use(vueClapButton);
Vue.use(VueSplide);

Vue.use(VueKinesis);

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkActiveClass: 'nav-item active',
  scrollBehavior: (to) => {
    if (to.hash) {
      return {selector: to.hash}
    } else {
      return { x: 0, y: 0 }
    }
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  created() {
    const userString = localStorage.getItem("user");
    if (userString) {
      const userData = JSON.parse(userString);
      let user = {
        userid: userData['user-id'],
        userpwd: userData['user-pwd'],
      }
      
      this.$store.dispatch("LOGIN", user); 
    };
    // axios.interceptors.response.use(
    //   console.log(axios.interceptors.response),
    //   response => response,
    //   error => {
    //     console.log(error);

    //     if (error.response.status === 401) {
    //       this.$store.dispatch("LOGOUT"); 
    //       localStorage.removeItem
    //     }
    //     return Promise.reject(error); 
    //   }
    // );
    http
      .get('/apt/hot10')
      .then(response=>{
          this.$store.state.hot10List = response.data.list;
      })
      .catch((error)=>{
          console.dir(error);
      })
  }
})

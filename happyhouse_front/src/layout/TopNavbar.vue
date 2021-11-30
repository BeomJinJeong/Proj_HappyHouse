<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <!-- <a class="navbar-brand" href="/">Dashboard</a> -->
      <button type="button"
              class="navbar-toggler navbar-toggler-right"
              :class="{toggled: $sidebar.showSidebar}"
              aria-controls="navigation-index"
              aria-expanded="false"
              aria-label="Toggle navigation"
              @click="toggleSidebar">
        <span class="navbar-toggler-bar burger-lines"></span>
        <span class="navbar-toggler-bar burger-lines"></span>
        <span class="navbar-toggler-bar burger-lines"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-end">
        <ul class="nav navbar-nav mr-auto">
          <li class="nav-item" @click="showModal">
            <a class="nav-link" href="#" data-toggle="dropdown">
              <i class="nc-icon nc-light-3" ></i> &nbsp; 추천매물

              <b-modal ref="hotModal" 
              :title-html="icon + ' 추천매물'" 
              hide-footer 
              >
                <!-- <p class="my-4">Hello from modal!</p> -->
                <hr>
                <div class="container-fluid" style="padding: 20px 0;">
                <splide
                  :options="options"
                  style="margin: auto auto;"
                  v-if="hot10List.length != 0"
                >
                  <splide-slide v-for="(apt, index) in hot10List" :key="index + 1">
                    <hot10 :apt="apt" :rank="index + 1" :close="closeModal" ></hot10>
                  </splide-slide>
                </splide>
                </div>
                <b-button class="mt-3" block @click="closeModal">Close</b-button>
              </b-modal>

            </a>
          </li>
          <!-- <base-dropdown tag="li">
            <template slot="title">
              <i class="nc-icon nc-planet"></i>
              <b class="caret"></b>
              <span class="notification">5</span>
            </template>
            <a class="dropdown-item" href="#">Notification 1</a>
            <a class="dropdown-item" href="#">Notification 2</a>
            <a class="dropdown-item" href="#">Notification 3</a>
            <a class="dropdown-item" href="#">Notification 4</a>
            <a class="dropdown-item" href="#">Another notification</a>
          </base-dropdown> -->
          <!-- <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nc-icon nc-zoom-split"></i>
              <span class="d-lg-block">&nbsp;Search</span>
            </a>
          </li> -->
        </ul>
        <ul class="navbar-nav ml-auto">
          <!-- <li class="nav-item">
            <a class="nav-link" href="#">
              Account
            </a>
          </li> -->
          <!-- <base-dropdown title="Dropdown">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <a class="dropdown-item" href="#">Something</a>
            <a class="dropdown-item" href="#">Another action</a>
            <a class="dropdown-item" href="#">Something</a>
            <div class="divider"></div>
            <a class="dropdown-item" href="#">Separated link</a>
          </base-dropdown> -->
          <li class="nav-item" v-if="getAccessToken">
              {{ user.username }}({{ user.userid }})님 환영합니다.
            <a @click.prevent="onClickLogout" class="nav-link"> logout
            </a>
          </li>
          <li class="nav-item" v-else>
            <a href="#/login" class="nav-link">
              LogIn
            </a>
            <a href="#/signup" class="nav-link">
              회원가입
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
  
  import { mapGetters } from "vuex";
  import hot10 from "@/components/hot10.vue";
  import http from "@/util/http-common.js";
  export default {
    components: {
      hot10,
    },
    computed: {
      ...mapGetters(["getAccessToken", "getUser"]),
      user(){
        return this.$store.getters.getUser;
      },
      routeName () {
        const {name} = this.$route
        return this.capitalizeFirstLetter(name)
      }
    },
    data () {
      return {
        icon : `<i class="nc-icon nc-light-3" ></i>`,
        activeNotifications: false,
        hot10List: [],
        options: {
        rewind: true,
        width: 1000,
        gap: "1rem",
        autoplay: true,
      },
      }
    },
    methods: {
      capitalizeFirstLetter (string) {
        return string.charAt(0).toUpperCase() + string.slice(1)
      },
      toggleNotificationDropDown () {
        this.activeNotifications = !this.activeNotifications
      },
      closeDropDown () {
        this.activeNotifications = false
      },
      toggleSidebar () {
        this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
      },
      hideSidebar () {
        this.$sidebar.displaySidebar(false)
      },
      onClickLogout() {
      this.$store
        .dispatch("LOGOUT")
        .then(() => this.$router.replace("/").catch(() => {}));
      },
      closeModal(){
        this.$refs['hotModal'].hide()
      },
      showModal(){
        this.$refs['hotModal'].show();
      }
    },
    mounted() {
      var parent = this;
      http.get("/apt/hot10").then(response => {
        console.dir(parent);
        parent.hot10List = response.data.list;
      });
    },

  }

</script>
<style>
.modal-dialog{
  max-width: 1000px !important;
}
</style>

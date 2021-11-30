<template>
  <div class="wrapper">
    <side-bar>
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link to="/board">
        <i class="nc-icon nc-bullet-list-67"></i>
        <p>공지사항</p>
      </sidebar-link>
      <sidebar-link to="/apt">
        <i class="nc-icon nc-zoom-split"></i>
        <p>실거래가 검색</p>
      </sidebar-link>
      <!-- <sidebar-link to="/hot10">
        <i class="nc-icon nc-zoom-split"></i>
        <p>추천 매물</p>
      </sidebar-link> -->
      <sidebar-link to="/test">
        <i class="nc-icon nc-pin-3"></i>
        <p>지도로 검색</p>
      </sidebar-link>
      <sidebar-link to="/fav">
        <i class="nc-icon nc-favourite-28"></i>
        <p>관심 매물</p>
      </sidebar-link>
      <!-- <sidebar-link to="/admin/overview">
        <i class="nc-icon nc-chart-pie-35"></i>
        <p>Dashboard</p>
      </sidebar-link> -->
      <sidebar-link to="/admin/info">
        <i class="nc-icon nc-circle-09"></i>
        <p>회원 정보</p>
      </sidebar-link>
      <!-- <sidebar-link to="/admin/table-list">
        <i class="nc-icon nc-notes"></i>
        <p>Table list</p>
      </sidebar-link>
      <sidebar-link to="/admin/typography">
        <i class="nc-icon nc-paper-2"></i>
        <p>Typography</p>
      </sidebar-link>
      <sidebar-link to="/admin/icons">
        <i class="nc-icon nc-atom"></i>
        <p>Icons</p>
      </sidebar-link> -->
      <sidebar-link to="/hospital">
        <i class="nc-icon nc-zoom-split"></i>
        <p>안심병원</p>
      </sidebar-link>
      <!-- <sidebar-link to="/admin/notifications">
        <i class="nc-icon nc-bell-55"></i>
        <p>Notifications</p>
      </sidebar-link> -->

    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click="toggleSidebar">

      </dashboard-content>

      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss">

</style>
<script>
  import TopNavbar from './TopNavbar.vue'
  import ContentFooter from './ContentFooter.vue'
  import DashboardContent from './Content.vue'
  import MobileMenu from './MobileMenu.vue'
  export default {
    components: {
      TopNavbar,
      ContentFooter,
      DashboardContent,
      MobileMenu
    },
    methods: {
      toggleSidebar () {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false)
        }
      }
    }
  }

</script>

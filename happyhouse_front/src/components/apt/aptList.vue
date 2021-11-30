<template>
<div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card class="strpied-tabled-with-hover" body-classes="table-full-width table-responsive">
            <template slot="header">
              <h4 class="card-title">실거래가 검색</h4>
              <p class="card-category">현재 매물의 실거래 가격을 검색해보세요</p>
              <hr>
              <apt-search></apt-search>
            </template>
            <apt-item :list="aptList"></apt-item>
            <pagenation :cnt="pageResult.count" URL="apt/list" dispatchName="getAptList"/>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LTable from 'src/components/aptTable.vue'
import Card from 'src/components/Cards/Card.vue'
// const tableColumns = ['Id', 'Name', 'Salary', 'Country', 'City']
import Pagenation from '@/components/paging/page.vue';
import AptSearch from '@/components/apt/aptSearch';
import AptItem from '@/components/apt/aptItem';
// const tableData = [];
import { mapActions } from 'vuex';
export default {
    name: 'aptList',
    data () {
      return {

      }
    },
    created() {
        this.$store.state.flag = 'apt';
        this.$store.dispatch('getAptList', {url: 'apt/list', key: this.$store.getters.aptkey, word: this.$store.getters.aptword, page: this.$store.getters.aptpage});    
    },
    components: {
        Pagenation,
        AptSearch,
        AptItem,
        LTable,
        Card
    },
    computed: {
        // aptList(){
        //     console.log(this.$store.getters.aptList);
        //     return this.$store.getters.aptList;
        // },
        pageResult(){
            return this.$store.getters.aptPr;
        },
        aptList(){
            return this.$store.getters.aptList;
        },
    },
    methods: {
        ...mapActions(['selectApt']),
        colorChange(flag) {
        this.isColor = flag;
        },
        selectApt(idx){
            this.$store.dispatch('selectApt', idx);
            this.$router.push('/aptDetail/');
        }
    },
}
</script>

<style>
 apt-search {
     margin: 20px auto;
 }
</style>
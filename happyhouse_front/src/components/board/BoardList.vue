<template>
<div class="container-fluid">
    <div class="row">
    <div class="col-12">
        <card class="strpied-tabled-with-hover" body-classes="table-full-width table-responsive">
        <template slot="header">
            <h4 class="card-title">공지사항</h4>
            <p class="card-category">중요 공지 필독</p>
            <hr>
        </template>
        <b-row>
            <b-col cols="3"></b-col>
            <b-col><board-search></board-search></b-col>
            <b-col cols="3"></b-col>
        </b-row>
        <hr>
        <board-item :boards="boards"></board-item>
        <b-row>
            <b-col></b-col>
            <b-col>
                <pagenation style="margin-left: 20%;" :cnt="pageResult.count" URL="board" dispatchName="getBoardsList" v-if="this.$store.getters.key == null" />
            </b-col>
            <b-col></b-col>
        </b-row>
        </card>
    </div>
    </div>
</div>
</template>
<script>
// import { mapState } from 'vuex';
import BoardItem from '@/components/board/BoardItem.vue';
import Pagenation from '@/components/Pagenation.vue';
import BoardSearch from '@/components/board/BoardSearch.vue';
export default {
    name: 'BoardList',
    components: {
        BoardItem,
        Pagenation,
        BoardSearch,
    },
    created(){
        this.$store.state.flag = 'board';
        let key = this.$store.getters.key;
        let word = this.$store.getters.word;
        let page = this.$store.getters.page;
        if(key != null && word != '') {
          console.log(this.URL+'/'+key+'/'+word)
          this.$store.dispatch('getBoardsList', {url: '/board'+'/'+key+'/'+word+'/'+page});
        } else {
          this.$store.dispatch('getBoardsList', {url: 'board/'+page});
        }
    },
    computed : {
        boards(){
            return this.$store.getters.boards;
        },
        pageResult() {
            return this.$store.getters.bpr;
        },
    },
};

</script>

<style>

</style>
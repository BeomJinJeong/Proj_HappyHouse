<template>
    <b-table striped :fields="['idx', 'subject', 'wdate', 'count']" :items="boards" @row-clicked="(item) => selectBoard(item.idx)"></b-table>
</template>

<script>
import { mapActions } from 'vuex';

export default {
    name: 'BoardItem',
    data() {
        return {
            isColor: false,
        };
    },
    props: {
        boards: Array,
    },
    methods: {
        ...mapActions(['selectBoards']),
        colorChange(flag) {
        this.isColor = flag;
        },
        selectBoard(idx){
            this.$router.push('/board/detail/'+idx);
        }
    },
}
</script>

<style scoped>
/* .img-list {
  width: 50px;
} */
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
<template>
  <b-container-fluid v-if="board" class="bv-example-row mr-4">
    <div class="mt-5"></div>
    <b-row class="pl-5 pr-5">
      <b-col></b-col>
      <b-col cols="3">
        <b-row>제목</b-row>
        <b-row
          ><h4>{{ board.subject }}</h4></b-row
        >
      </b-col>
      <b-col cols="3">
        <b-row>작성일자</b-row>
        <b-row
          ><h4>{{ board.wdate }}</h4></b-row
        >
      </b-col>
      <b-col>
        <b-row cols="1">조회수</b-row>
        <b-row
          ><h4>{{ board.count }}회</h4></b-row
        >
      </b-col>
      <b-col>
        <!-- <b-row>전용면적</b-row>
        <b-row><h4>{{apt.area}}</h4></b-row> -->
      </b-col>
      <b-col></b-col>
    </b-row>
    <hr />
    <hr />
    <b-card class="p-3">
      {{ board.content }}
    </b-card>
  </b-container-fluid>
</template>

<script>
export default {
  name: "BoardDetail",
  created() {
    this.$store.dispatch("selectBoard", this.$route.params["idx"]);
  },
  computed: {
    board() {
      return this.$store.getters.board;
    }
  }
};
</script>

<style>
.text {
  text-align: right;
}
.cont {
  text-align: left;
}
</style>

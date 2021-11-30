<template>
  <b-container-fluid class="bv-example-row mr-4">
    <div class="mt-5"></div>
    <b-row class="pl-5 pr-5">
      <b-col></b-col>
      <b-col cols="3">
        <b-row>아파트명</b-row>
        <b-row
          ><h4>{{ apt.aptName }}</h4></b-row
        >
      </b-col>
      <b-col cols="1">
        <b-row>전용면적</b-row>
        <b-row
          ><h4>{{ apt.area }}㎡</h4></b-row
        >
      </b-col>
      <b-col>
        <b-row>거래금액</b-row>
        <b-row
          ><h4>{{ apt.dealAmount }}(천) ~</h4></b-row
        >
      </b-col>
      <b-col>
        <!-- <b-row>전용면적</b-row>
        <b-row><h4>{{apt.area}}</h4></b-row> -->
      </b-col>
      <b-col>
        <b-row>소속 시/구</b-row>
        <b-row
          ><h4>{{ sigu }}</h4></b-row
        >
      </b-col>
      <b-col></b-col>
    </b-row>
    <hr />
    <div class="table_style" style="margin-left: 15%;">
      <b-col></b-col>
      <b-col>
        <ul class="header">
          <li class="column1">준공연도</li>
          <li class="column2">{{ apt.buildYear }}년</li>
          <li class="column3">거래일자</li>
          <li class="column4">
            {{ apt.dealYear }}.{{ apt.dealMonth }}.{{ apt.dealDay }}
          </li>
        </ul>
        <ul>
          <li class="column1">해당동</li>
          <li class="column2">{{ apt.dong }}</li>
          <li class="column3">해당층</li>
          <li class="column4">{{ apt.floor }}</li>
        </ul>
      </b-col>
      <b-col></b-col>
    </div>
    <div class="m-5"></div>
    <hr />
    <b-table>
      <b-thead>
        <b-tr>
          <b-th>
            건물유형
          </b-th>
          <b-th>
            건물유형
          </b-th>
        </b-tr>
      </b-thead>
    </b-table>
    <b-row>
      <b-col></b-col>
      <b-col cols="7"
        ><img :src="dealImgs[apt.no % 10]" style="width: 100%"
      /></b-col>
      <b-col></b-col>
    </b-row>
  </b-container-fluid>
</template>

<script>
import http from '@/util/http-common.js';
export default {
  name: "BoardDetail",
  data() {
    return {
      apt: {},
      dealImgs: [],
      sigu: '',
    };
  },
  created() {
    this.apt = this.$store.getters.apt;
    for (let i = 1; i <= 10; i++) {
      this.dealImgs.push(require("../assets/img/InnerImg/집" + i + ".jpg"));
    }
  },
  // computed: {
  //   apt() {
  //     console.log(this.$store.getters.apt);
  //     return this.$store.getters.apt;
  //   }
  // }
  mounted() {
    http.get('/apt/code/' + this.apt.code)
    .then(({data}) => {
      console.log(data);
      this.sigu = data;
    });
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
.index {
  text-align: right;
}
.val {
  text-align: left;
}
.table_style {
  width: 90%;
  position: relative;
  padding-top: 10px;
  padding-left: 10%;
}
.table_style ul {
  clear: left;
  margin: 0;
  padding: 0;
  list-style-type: none;
}
.table_style ul li {
  float: left;
  margin: 0;
  padding: 2px 1px;
  height: 40px;
}
.table_style ul .column1 {
  width: 15%;
  font-weight: bold;
}
.table_style ul .column2 {
  width: 25%;
}
.table_style ul .column3 {
  width: 15%;
  font-weight: bold;
}
.table_style ul .column4 {
  width: 25%;
}
</style>

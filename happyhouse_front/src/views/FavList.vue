<template>
  <b-container-fluid class="FavList" fluid="lg">
    <div style="margin-left: 20px; margin-right: -10px;">
      <h3>관심목록</h3>
      <h6>'{{ userName }}' 님이 관심지역으로 추가한 매물 목록입니다</h6>
      <hr />
      <b-row>
        <b-col cols="5">
          <span @click="SortByDealAmount()" class="mouseover" style="position:relative; left:83%">
            <img src="@/assets/img/정렬.png" style="width:20px">
            가격순 정렬</span>
          <b-list-group
            v-if="favlist != null"
            style="height: 700px; overflow:scroll;"
          >
            <b-list-group-item
              class="flex-column align-items-start"
              v-for="(apt, index) in favlist"
              :key="index"
            >
              <b-row>
                <b-col>
                  <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1 mouseover" @click="test(apt)">
                      {{ apt.aptName }}
                    </h5>
                    <small>{{ apt.dong }}</small>
                  </div>

                  <p class="mb-1">
                    아파트 / {{ apt.area }} (평) / {{ apt.dealAmount }}
                  </p>

                  <small
                    >{{ apt.dealYear }}.{{ apt.dealMonth }}.{{
                      apt.dealDay
                    }}</small
                  >
                  <like
                    :apt="apt"
                    style="position:absolute; top:30%; right:2%"
                  ></like>
                </b-col>
              </b-row>
            </b-list-group-item>
          </b-list-group>
          <!-- <div v-for="(fav, index) in favlist" :key="index">
          {{fav.aptName}} / {{fav.dong}} / {{fav.area}}평 / {{fav.dealAmount}}만원
        </div> -->
        </b-col>

        <b-col cols="7">
          <b-col cols="11">
            <card>
              <b-row>
                <b-col cols="5">
                  <img :src="dealImgs[apt.no % 10]" style="width: 100%;" />
                </b-col>
                <b-col cols="7">
                  <div>
                    <h4>{{ apt.aptName }}</h4>
                  </div>
                  <b-row>
                    <b-col>매물번호 : {{ apt.no }} </b-col>
                  </b-row>
                  <b-row>
                    <b-col>층 : {{ apt.floor }} </b-col>
                  </b-row>
                  <b-row>
                    <b-col>지역 : {{ apt.dong }} </b-col>
                  </b-row>
                  <b-row>
                    <b-col>건축년도 : {{ apt.buildYear }}년 </b-col>
                  </b-row>
                  <b-row>
                    <b-col>전용면적 : {{ apt.area }}평 </b-col>
                  </b-row>
                  <b-row>
                    <b-col>매매가 : {{ apt.dealAmount }}만원 </b-col>
                  </b-row>
                </b-col>
              </b-row>
            </card>
            <div class="m-3"></div>
            <b-row>
              <b-col>
                <!-- <div class="row"> -->
                <!-- <div class="col-md-6"> -->
                <chart-card
                  :chart-data="barChart.data"
                  :chart-options="barChart.options"
                  :chart-responsive-options="barChart.responsiveOptions"
                  chart-type="Bar"
                  v-if="cnt == 7"
                >
                  <template slot="header">
                    <h4 class="card-title">주변 편의시설</h4>
                    <p class="card-category"></p>
                  </template>
                  <template slot="footer">
                    <div class="legend">
                      <i class="fa fa-circle text-info"></i> 50m 이내
                      <i class="fa fa-circle text-danger"></i> 250m 이내
                      <i class="fa fa-circle text-warning"></i> 500m 이내
                    </div>
                  </template>
                </chart-card>
                <!-- </div> -->
                <!-- </div> -->
              </b-col>
            </b-row>
          </b-col>
        </b-col>
      </b-row>
    </div>
  </b-container-fluid>
</template>

<script>
import http from "@/util/http-common";
import ChartCard from "src/components/Cards/ChartCard.vue";
import Card from "@/components/Cards/Card.vue";
import Like from "@/components/fav/like.vue";
export default {
  name: "FavList",
  components: {
    ChartCard,
    Card,
    Like
  },
  data() {
    return {
      dealImgs: [],
      apt: {},
      codes: ["SW8", "BK9", "MT1", "PM9", "OL7", "CE7", "CS2"],
      cnt: 0,
      mapping: {
        BK9: 0,
        MT1: 1,
        PM9: 2,
        OL7: 3,
        CE7: 4,
        CS2: 5
      },
      favlist: [],
      type: ["", "info", "success", "warning", "danger"],
      // userName: this.$store.state.user.userName,
      // userId: this.$store.state.user.userId,
      // userId: JSON.parse(localStorage.getItem('user'))['user-id'],
      // userName: JSON.parse(localStorage.getItem('user'))['user-name'],
      userId: "",
      userName: "",
      barChart: {
        data: {
          labels: [
            "지하철역",
            "은행",
            "마트",
            "약국",
            "주유소",
            "카페",
            "편의점"
          ],
          series: [
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0]
          ]
        },
        options: {
          seriesBarDistance: 10,
          axisX: {
            showGrid: false
          },
          height: "245px"
        },
        responsiveOptions: [
          [
            "screen and (max-width: 800px)",
            {
              seriesBarDistance: 5,
              axisX: {
                labelInterpolationFnc(value) {
                  return value[0];
                }
              }
            }
          ]
        ]
      }
    };
  },
  created() {
    for (let i = 1; i <= 10; i++) {
      this.dealImgs.push(require("../assets/img/InnerImg/집" + i + ".jpg"));
    }
    if (JSON.parse(localStorage.getItem("user")) == null) {
      // alert("로그인 후 이용해주세요");
      this.notifyVue("top", "center");
      this.$router.replace("/");
    } else {
      this.userId = JSON.parse(localStorage.getItem("user"))["user-id"];
      this.userName = JSON.parse(localStorage.getItem("user"))["user-name"];
      console.log("userid : " + this.userId);
      // console.log("Local-userid : " + JSON.parse(localStorage.getItem('user'))['user-name']);
      http
        .get("/fav/selectLike/" + this.userId)
        .then(response => {
          this.favlist = response.data.list;
          console.dir(this.favlist);
          if (this.favlist.length != 0) {
            this.apt = this.favlist[0];
            this.test(this.apt);
          }
        })
        .catch(error => {
          console.dir(error);
        });
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7668450e4032b9f93f20fe4ceee3b7bc&libraries=drawing,services";
      document.head.appendChild(script);
    }
  },
  methods: {
    notifyVue(verticalAlign, horizontalAlign) {
      const color = Math.floor(Math.random() * 4 + 1);
      this.$notifications.notify({
        message: `<span>앗 <b>로그인이 되어있지 않아요</b> <br> 로그인 후 가능한 서비스입니다.</span>`,
        icon: "nc-icon nc-app",
        horizontalAlign: horizontalAlign,
        verticalAlign: verticalAlign,
        type: this.type[color]
      });
    },
    initMap() {
      this.place = new kakao.maps.services.Places();
    },
    async test(apt) {
      this.apt = apt;
      let res = await http.get("/apt/info/" + apt.dong + "/" + apt.aptName);
      let ai = res.data;

      this.cnt = 0;
      this.barChart.data.series = [
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0]
      ];

      this.codes.forEach(code => {
        this.nearby(code, ai);
      });
    },
    placeSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        let curCode = data[0].category_group_code;
        for (var i = 0; i < data.length; i++) {
          let curDist = data[i].distance;

          ++this.barChart.data.series[2][this.codes.indexOf(curCode)];
          if (50 < curDist && curDist <= 250) {
            ++this.barChart.data.series[1][this.codes.indexOf(curCode)];
          }
          if (curDist <= 50) {
            ++this.barChart.data.series[0][this.codes.indexOf(curCode)];
          }
        }
      }
      this.cnt += 1;
    },
    nearby(code, ai) {
      return this.place.categorySearch(code, this.placeSearchCB, {
        x: ai.lng,
        y: ai.lat,
        radius: 500
      });
    },
    SortByDealAmount() {
      this.favlist = null;
      http.get("/fav/selectLike/byDealAmount/" + this.userId).then(response => {
        console.dir(response.data.list);
        console.dir(this);
        this.favlist = response.data.list;
      });
    },
    getLikeAvg() {
      http.get("/fav/getLikeAvg/" + this.userId).then(response => {
        console.log(response.data.avgDealAmount);
        console.log(response.data.avgArea);
        console.log(response.data.avgDPA);
      });
    }
  }
};
</script>

<style scoped>
.FavList {
  width: 100%;
  margin: 0 10px;
}
.mouseover {
  cursor: pointer;
}
</style>

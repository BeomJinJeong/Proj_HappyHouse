<template>
  <b-container fluid>
    <b-row>
      <b-col cols="8" class="m-3">
        <div id="map" style="width: 100%; height: 800px"></div>

        <button
          style="position: absolute; top: 10px; left: 25px; z-index: 1; width: 110px; height: 50px; background-color: white; border: solid 2px rgba(0, 0, 0, .2); border-radius: 5%;"
          @click="selectOverlay('RECTANGLE')"
        >
          범위 지정하기
        </button>
      </b-col>
      <b-col>
        <b-row class="my-3">
          <b-col cols="3">
            <b-form-select v-model="key">
              <b-form-select-option :value="null"
                >키워드 선택</b-form-select-option
              >
              <b-form-select-option value="dong">동</b-form-select-option>
              <b-form-select-option value="AptName"
                >아파트명</b-form-select-option
              >
            </b-form-select>
          </b-col>
          <b-col cols="7">
            <b-form-select v-if="key == 'dong'" v-model="word">
              <b-form-select-option value=""
                >검색어를 선택해주세요.</b-form-select-option
              >
              <b-form-select-option
                :value="dong"
                v-for="dong in dongs"
                :key="dong"
              >
                {{ dong }}
              </b-form-select-option>
            </b-form-select>
            <b-form-input
              v-if="key == 'AptName'"
              placeholder="검색어 입력"
              @keypress.enter="searchArea"
              v-model="word"
            />
          </b-col>
          <b-col cols="2">
            <b-button
              variant="outline-primary"
              @click="searchArea"
              v-if="key != null"
              >검색</b-button
            >
          </b-col>
        </b-row>
        <br />
        <b-list-group
          :data="selectedAreaAptDeals"
          style="height: 800px; overflow:scroll;"
        >
          <b-list-group-item
            v-for="ad in selectedAreaAptDeals"
            :key="ad.no"
            class="flex-column align-items-start"
          >
            <b-card
              :img-src="dealImgs[ad.no % 10]"
              img-alt="Card image"
              img-top
              img-height="200px"
              @click="selectApt(ad.no)"
              class="mouseover"
            >
              <b-card-text>
                <div class="d-flex justify-content-between">
                  <h4>매매가 : {{ ad.dealAmount }} (만원)</h4>
                  <p>
                    등록일 : {{ ad.dealYear }} / {{ ad.dealMonth }} /
                    {{ ad.dealDay }}
                  </p>
                </div>

                <div class="d-flex justify-content-between">
                  <h4>면적 : {{ ad.area }} (평)</h4>
                  <p>층수 : {{ ad.floor }}</p>
                </div>
              </b-card-text>
            </b-card>
            <like :apt="ad" @click.prevent=""></like>
            <!-- <div class="d-flex w-100 justify-content-between">
              <h5 class="mb-1" @click="selectApt(ad.no)">{{ ad.aptName }}</h5>
              <small
                >{{ ad.dealYear }} / {{ ad.dealMonth }} /
                {{ ad.dealDay }}</small
              >
              <like :apt="ad"></like>
            </div>

            <p class="mb-1">{{ ad.dong }}</p>

            <small>{{ ad.floor }}</small>
            {{ ad.aptName }} -->
          </b-list-group-item>
        </b-list-group>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="4">
        <b-card
          :title="aptInfo.aptName"
          :img-src="imgSrc"
          img-alt="Image"
          img-top
          tag="article"
          style="width: 100%; "
          class="mb-2"
          v-if="aptInfo != null && sigu != null"
          img-height="250"
        >
          <hr />
          <b-card-text>
            <h5>{{ sigu }} {{ aptInfo.dong }}</h5>
            <br />
            <p>건축연도 : {{ aptInfo.buildYear }}</p>
            <p>거래가능한 방 : {{ selectedAreaAptDeals.length }}개</p>
          </b-card-text>
        </b-card>
      </b-col>
      <b-col>
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
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import like from "@/components/fav/like.vue";
import ChartCard from "src/components/Cards/ChartCard.vue";
// import ChartCard from "src/components/Cards/ChartCard.vue";

export default {
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
  data() {
    return {
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false
      },
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
            "screen and (max-width: 640px)",
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
      },
      dealImgs: [],
      imgSrc: "",
      selectedAreaApts: [],
      key: null,
      word: "",
      dongs: [],
      map: null,
      markers: [],
      manager: null,
      place: null,
      rangeMarkers: [],
      infowindow: null,
      subwayMarkers: [],
      codes: ["SW8", "BK9", "MT1", "PM9", "OL7", "CE7", "CS2"],
      cnt: 0,
      markerImageSrc:
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png",
      mapping: {
        BK9: 0,
        MT1: 1,
        PM9: 2,
        OL7: 3,
        CE7: 4,
        CS2: 5
      },
      customOverlay: null,
      aptInfo: null,
      sigu: null
    };
  },
  components: {
    ChartCard,
    like
  },
  created() {
    http.get("/apt/dong").then(response => {
      this.dongs = response.data;
    });
    this.$store.dispatch("resetSelectedAreaAptDeals");
    for (let i = 1; i <= 10; i++) {
      this.dealImgs.push(require("../assets/img/InnerImg/집" + i + ".jpg"));
    }
  },
  computed: {
    selectedAreaAptDeals() {
      return this.$store.getters.selectedAreaAptDeals;
    }
  },
  methods: {
    selectApt(no) {
      this.$store.dispatch("selectApt", no);
      this.$router.push("/aptDetail");
    },
    async initMap() {
      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      this.customOverlay = new kakao.maps.CustomOverlay();
      this.place = new kakao.maps.services.Places();
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(37.5743822, 126.9688505),
        level: 3
      };

      var map = new kakao.maps.Map(container, options);
      map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
      this.map = map;
      let parent = this;
      kakao.maps.event.addListener(map, "rightclick", function() {
        parent.infowindow.close();
        parent.customOverlay.setMap(null);
      });
      var drawingOptions = {
        // Drawing Manager를 생성할 때 사용할 옵션입니다
        map: map, // Drawing Manager로 그리기 요소를 그릴 map 객체입니다
        drawingMode: [
          // Drawing Manager로 제공할 그리기 요소 모드입니다
          kakao.maps.Drawing.OverlayType.RECTANGLE
        ],
        // 사용자에게 제공할 그리기 가이드 툴팁입니다
        // 사용자에게 도형을 그릴때, 드래그할때, 수정할때 가이드 툴팁을 표시하도록 설정합니다
        guideTooltip: ["draw", "edit"],
        rectangleOptions: {
          draggable: false,
          removable: false,
          editable: false,
          strokeColor: "#39f", // 외곽선 색
          fillColor: "#39f", // 채우기 색
          fillOpacity: 0.3 // 채우기색 투명도
        }
      };

      // 위에 작성한 옵션으로 Drawing Manager를 생성합니다
      var manager = new kakao.maps.Drawing.DrawingManager(drawingOptions);
      this.manager = manager;

      manager.addListener("drawend", function(data) {
        parent.cnt = 0;
        parent.barChart.data.series = [
          [0, 0, 0, 0, 0, 0, 0],
          [0, 0, 0, 0, 0, 0, 0],
          [0, 0, 0, 0, 0, 0, 0]
        ];
        parent.aptInfo = null;
        if (parent.markers.length != 0) {
          parent.markers.forEach(marker => {
            marker.setMap(null);
          });
          parent.markers = [];
        }
        if (parent.subwayMarkers.length != 0) {
          parent.subwayMarkers.forEach(marker => {
            marker.setMap(null);
          });
          parent.subwayMarkers = [];
        }
        parent.infowindow.close();
        let lts = this.getData().rectangle;
        let lt = lts[lts.length - 1];
        let sLat = lt.sPoint.y,
          lLat = lt.ePoint.y,
          sLng = lt.sPoint.x,
          lLng = lt.ePoint.x;
        let result = [];

        let markerResult = [];

        http
          .get("apt/infoList/" + sLat + "/" + lLat + "/" + sLng + "/" + lLng)
          .then(response => {
            result = response.data;

            var positions = [];
            result.forEach(element => {
              positions.push({
                title:
                  element.dong +
                  "/" +
                  element.aptName +
                  "/" +
                  element.no +
                  "/" +
                  element.code,
                latlng: new kakao.maps.LatLng(element.lat, element.lng)
              });
            });

            var imageSrc =
              "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
            var imageSize = new kakao.maps.Size(24, 35);
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

            for (var i = 0; i < positions.length; i++) {
              var marker = new kakao.maps.Marker({
                map: parent.map,
                position: positions[i].latlng,
                title: positions[i].title,
                image: markerImage
              });

              markerResult.push(marker);
              parent.rangeMarkers.push(marker);
            }
            markerResult.forEach(marker => {
              const token = marker.getTitle().split("/");
              let dong = token[0];
              let aptName = token[1];
              let no = token[2];
              let code = token[3];

              kakao.maps.event.addListener(marker, "click", async function() {
                parent.imgSrc = require("../assets/img/AptImg/" +
                  ((no % 10) + 1) +
                  ".jpg");
                parent.aptInfo = null;
                parent.sigu = null;
                let a = await http.get("apt/info/" + no);
                parent.aptInfo = a.data;
                let b = await http.get("apt/code/" + code);
                parent.sigu = b.data;
                var mi = new kakao.maps.MarkerImage(
                  "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
                  new kakao.maps.Size(24, 35)
                );
                parent.rangeMarkers.forEach(mark => {
                  mark.setImage(mi);
                });
                mi = new kakao.maps.MarkerImage(
                  "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
                  new kakao.maps.Size(45, 60)
                );
                marker.setImage(mi);
                parent.customOverlay.setMap(null);
                parent.cnt = 0;
                parent.barChart.data.series = [
                  [0, 0, 0, 0, 0, 0, 0],
                  [0, 0, 0, 0, 0, 0, 0],
                  [0, 0, 0, 0, 0, 0, 0]
                ];
                // parent.barChart.data.series[0] = [0, 0, 0, 0, 0, 0];
                parent.infowindow.close();
                parent.subwayMarkers.forEach(sm => {
                  sm.setMap(null);
                });
                parent.subwayMarkers = [];
                parent.place.categorySearch(
                  "SW8",
                  function(data, status, pagination) {
                    if (status === kakao.maps.services.Status.OK) {
                      var content =
                        '<div class ="label"><span class="left"></span><span class="center">역세권!</span><span class="right"></span></div>';
                      var position = new kakao.maps.LatLng(
                        marker.getPosition().Ma,
                        marker.getPosition().La
                      );
                      parent.customOverlay.setPosition(position);
                      parent.customOverlay.setContent(content);
                      console.dir(parent.customOverlay);
                      parent.customOverlay.setMap(parent.map);
                    }
                  },
                  {
                    x: marker.getPosition().La,
                    y: marker.getPosition().Ma,
                    radius: 250
                  }
                );
                http
                  .get("apt/dealList/" + dong + "/" + aptName)
                  .then(response => {
                    parent.$store.dispatch(
                      "setSelectedAreaAptDeals",
                      response.data
                    );
                  });
                parent.codes.forEach(code => {
                  parent.nearby(code, marker);
                });
              });
            });
          });
      });
    },
    selectOverlay(type) {
      this.manager.cancel();

      this.manager.select(kakao.maps.Drawing.OverlayType[type]);
    },
    undo() {
      this.manager.undo();
    },
    redo() {
      this.manager.redo();
    },
    displayMarker(place, code) {
      let parent = this;
      var marker = null;
      if (code != "SW8") {
        let imageSize = new kakao.maps.Size(27, 28);
        let imageOptions = {
          spriteSize: new kakao.maps.Size(72, 208),
          spriteOrigin: new kakao.maps.Point(46, parent.mapping[code] * 36)
        };
        let markerImage = new kakao.maps.MarkerImage(
          this.markerImageSrc,
          imageSize,
          imageOptions
        );
        marker = new kakao.maps.Marker({
          map: parent.map,
          position: new kakao.maps.LatLng(place.y, place.x),
          image: markerImage
        });
      } else {
        var imageSrc = require("../assets/img/subway.png");
        var imageSize = new kakao.maps.Size(27, 28);
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        marker = new kakao.maps.Marker({
          map: parent.map,
          position: new kakao.maps.LatLng(place.y, place.x),
          image: markerImage
        });
      }
      this.subwayMarkers.push(marker);

      kakao.maps.event.addListener(marker, "click", function() {
        var content =
          '<div class="placeinfo">' +
          '   <a class="title" href="' +
          place.place_url +
          '" target="_blank" title="' +
          place.place_name +
          '">' +
          place.place_name +
          "</a>";

        if (place.road_address_name) {
          content +=
            '    <span title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            "</span>" +
            '  <span class="jibun" title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ")</span>";
        } else {
          content +=
            '    <span title="' +
            place.address_name +
            '">' +
            place.address_name +
            "</span>";
        }

        content +=
          '    <span class="tel">' +
          place.phone +
          "</span>" +
          "</div>" +
          '<div class="after"></div>';

        parent.infowindow.setContent(content);
        parent.infowindow.open(parent.map, marker);
      });
    },
    placeSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        let curCode = data[0].category_group_code;
        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i], curCode);
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
    nearby(code, marker) {
      return this.place.categorySearch(code, this.placeSearchCB, {
        x: marker.getPosition().La,
        y: marker.getPosition().Ma,
        radius: 500
      });
    },
    async searchArea() {
      if (this.key == null) {
        // alert("검색 키워드를 선택해주세요!");
        this.notifyVue("검색 실패!", "검색 키워드를 선택해주세요!");
      } else {
        if (this.word == "") {
          if (this.key == "dong") {
            this.notifyVue("검색 실패!", "검색할 동을 선택해주세요!");
          } else {
            this.notifyVue("검색 실패!", "검색어를 입력해주세요!");
          }
        } else {
          this.cnt = 0;
          this.barChart.data.series = [
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0]
          ];
          this.aptInfo = null;
          this.rangeMarkers.forEach(element => {
            element.setMap(null);
          });
          this.markers.forEach(element => {
            element.setMap(null);
          });
          this.subwayMarkers.forEach(element => {
            element.setMap(null);
          });
          this.infowindow.close();
          this.subwayMarkers = [];
          this.rangeMarkers = [];
          this.markers = [];
          await this.mark();
          this.listPush();
          var overlays = this.manager.getOverlays().rectangle;
          for (var idx = 0; idx < overlays.length; idx++) {
            this.manager.remove(overlays[idx]);
          }
        }
      }
    },
    async mark() {
      try {
        const response = await http.get(
          "apt/infoList/" + this.key + "/" + this.word
        );
        this.selectedAreaApts = response.data;
        var lat = this.selectedAreaApts[0].lat;
        var lng = this.selectedAreaApts[0].lng;
        var move = new kakao.maps.LatLng(lat, lng);
        this.map.setCenter(move);
      } catch (error) {
        console.error(error);
      }
    },
    notifyVue(msg1, msg2) {
      const color = Math.floor(Math.random() * 4 + 1);
      var verticalAlign = "top",
        horizontalAlign = "center";
      this.$notifications.notify({
        message: `<span><b>${msg1}</b><br />${msg2}</span>`,
        icon: "nc-icon nc-app",
        horizontalAlign: horizontalAlign,
        verticalAlign: verticalAlign,
        type: this.type[color]
      });
    },
    async listPush() {
      var positions = [];
      this.selectedAreaApts.forEach(element => {
        positions.push({
          title:
            element.dong +
            "/" +
            element.aptName +
            "/" +
            element.no +
            "/" +
            element.code,
          latlng: new kakao.maps.LatLng(element.lat, element.lng)
        });
      });
      let parent = this;
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      var imageSize = new kakao.maps.Size(24, 35);
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      for (var i = 0; i < positions.length; i++) {
        var marker = new kakao.maps.Marker({
          map: this.map,
          position: positions[i].latlng,
          title: positions[i].title,
          image: markerImage
        });

        this.markers.push(marker);
      }
      this.markers.forEach(marker => {
        const token = marker.getTitle().split("/");
        let dong = token[0];
        let aptName = token[1];
        let no = token[2];
        let code = token[3];
        kakao.maps.event.addListener(marker, "click", async function() {
          parent.imgSrc = require("../assets/img/AptImg/" +
            ((no % 10) + 1) +
            ".jpg");
          parent.aptInfo = null;
          parent.sigu = null;
          let a = await http.get("apt/info/" + no);
          parent.aptInfo = a.data;
          let b = await http.get("apt/code/" + code);
          parent.sigu = b.data;
          var mi = new kakao.maps.MarkerImage(
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
            new kakao.maps.Size(24, 35)
          );
          parent.markers.forEach(mark => {
            mark.setImage(mi);
          });
          mi = new kakao.maps.MarkerImage(
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
            new kakao.maps.Size(45, 60)
          );
          marker.setImage(mi);
          parent.cnt = 0;
          parent.barChart.data.series = [
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0]
          ];
          parent.infowindow.close();
          parent.subwayMarkers.forEach(sm => {
            sm.setMap(null);
          });
          parent.subwayMarkers = [];
          parent.place.categorySearch(
            "SW8",
            function(data, status, pagination) {
              if (status === kakao.maps.services.Status.OK) {
                var content =
                  '<div class ="label"><span class="left"></span><span class="center">역세권!</span><span class="right"></span></div>';
                var position = new kakao.maps.LatLng(
                  marker.getPosition().Ma,
                  marker.getPosition().La
                );
                parent.customOverlay.setPosition(position);
                parent.customOverlay.setContent(content);
                console.dir(parent.customOverlay);
                parent.customOverlay.setMap(parent.map);
              }
            },
            {
              x: marker.getPosition().La,
              y: marker.getPosition().Ma,
              radius: 250
            }
          );
          http.get("apt/dealList/" + dong + "/" + aptName).then(response => {
            parent.$store.dispatch("setSelectedAreaAptDeals", response.data);
          });
          parent.codes.forEach(code => {
            parent.nearby(code, marker);
          });
        });
      });
    }
  }
};
</script>

<style>
#map {
  width: 100%;
  height: 800px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
.label {
  margin-bottom: 96px;
}
.label * {
  display: inline-block;
  vertical-align: top;
}
.label .left {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png")
    no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  vertical-align: top;
  width: 7px;
}
.label .center {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png)
    repeat-x;
  display: inline-block;
  height: 24px;
  font-size: 12px;
  line-height: 24px;
}
.label .right {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px
    0 no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  width: 6px;
}
.mouseover {
  cursor: pointer;
}
</style>

<template>
  <div class="content" style="padding: 0; margin-left: -15px; background-size: cover;" :style="{backgroundImage: 'url(' + this.img + ')'}">
    <div class="container-fluid" style="padding: 20px 0;">
      <splide
        :options="options"
        style="margin: auto auto;"
        v-if="hot10List.length != 0"
      >
        <splide-slide v-for="(apt, index) in hot10List" :key="index + 1">
          <hot10 :apt="apt" :rank="index + 1" ></hot10>
        </splide-slide>
      </splide>
      <b-row align="center">
        <b-col></b-col>
        <b-col><img class="btnImg" :src="this.btnImg" style="width: 100%; margin: -2px auto;" alt=""/></b-col>
        <b-col><img class="btnImg" :src="this.btnImg" style="width: 100%; margin: -2px auto;" alt=""/></b-col>
        <b-col></b-col>
      </b-row>
    </div>
  </div>
</template>
<script>
import hot10 from "@/components/hot10.vue";
import http from "@/util/http-common.js";
export default {
  name: "test",
  components: {
    hot10,
  },
  data() {
    return {
      hot10List: [],
      options: {
        rewind: true,
        width: 1000,
        gap: "1rem",
        autoplay: true,
      },
      img : 'http://ojsfile.ohmynews.com/STD_IMG_FILE/2008/0629/IE000932779_STD.jpg',
      btnImg : 'http://pngimg.com/uploads/buttons/buttons_PNG176.png'
    };
  },
  mounted() {
    var parent = this;
    http.get("/apt/hot10").then(response => {
      console.dir(parent);
      parent.hot10List = response.data.list;
    });
  },
};
</script>
<style>
.btnImg {
  width: 400px;
  height: 200px;
}
button {
  padding: 0;
  border: 0;
  background-image: none;
}
</style>

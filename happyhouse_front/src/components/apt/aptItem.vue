<template>
  <b-list-group>
    <b-list-group-item
      @click="selectApt(apt.no)"
      class="flex-column align-items-start"
      v-for="(apt, index) in list"
      :key="index"
    >
      <b-row>
        <b-col cols="1"
          ><img :src="dealImgs[apt.no % 10]" style="width: 100%; height: 100px;"
        /></b-col>
        <b-col>
          <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">{{ apt.aptName }}</h5>
            <small>{{ apt.dong }}</small>
          </div>

          <p class="mb-1">
            아파트 / {{ apt.area }} (평) / {{ apt.dealAmount }}
          </p>

          <small
            >{{ apt.dealYear }}.{{ apt.dealMonth }}.{{ apt.dealDay }}</small
          >
        </b-col>
      </b-row>
    </b-list-group-item>
  </b-list-group>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "AptItem",
  data() {
    return {
      dealImgs: [],
      isColor: false
    };
  },
  created() {
    for (let i = 1; i <= 10; i++) {
      this.dealImgs.push(require("../../assets/img/InnerImg/집" + i + ".jpg"));
    }
  },
  props: {
    apt: Object,
    list: Array
  },
  methods: {
    ...mapActions(["selectApt"]),
    colorChange(flag) {
      this.isColor = flag;
    },
    selectApt(idx) {
      this.$store.dispatch("selectApt", idx);
      this.$router.push("/aptDetail/");
    }
  }
};
</script>

<style scoped>
/* .img-list {
  width: 50px;
} */
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>

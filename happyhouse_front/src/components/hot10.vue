<template>
  <card class="card bg-white">
    <img
      :src="dealImgs[apt.no % 10]"
      alt="..."
      style="width: 920px; height: 500px;"
      @click="selectApt(apt.no)"
    />
    <card class="mt-3 pl-5 pr-5">
      <b-row>
        <b-col cols="9">
          <b-row>
            <h3>{{ apt.aptName }} ( {{ apt.dong }} )</h3>
          </b-row>
          <b-row>
            <h5>{{ apt.floor }}층 ({{ apt.area }})</h5>
          </b-row>
        </b-col>
        <b-col>
          <h3>추천순위 {{ rank }}위</h3>
        </b-col>
      </b-row>
    </card>
  </card>
</template>

<script>
import Card from "src/components/Cards/Card.vue";

export default {
  components: {
    Card
  },
  props: {
    apt: Object,
    rank: Number,
    close: Function
  },
  data() {
    return {
      dealImgs: []
    };
  },
  created() {
    for (let i = 1; i <= 10; i++) {
      this.dealImgs.push(require("../assets/img/InnerImg/집" + i + ".jpg"));
    }
  },
  methods: {
    getClasses(index) {
      var remainder = index % 3;
      if (remainder === 0) {
        return "col-md-3 col-md-offset-1";
      } else if (remainder === 2) {
        return "col-md-4";
      } else {
        return "col-md-3";
      }
    },
    selectApt(no) {
      this.$store.dispatch("selectApt", no);
      this.close();
      this.$router.push("/aptDetail");
    }
  }
};
</script>

<style></style>

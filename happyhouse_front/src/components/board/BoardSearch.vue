<template>
  <b-row class="mt-4 mb-4">
    <b-col cols="3" style="margin-right: -25px;">
      <b-form-select :value="key" v-model="selectedKey">
        <b-form-select-option :value="null"
          >검색 키워드를 선택해주세요.</b-form-select-option
        >
        <b-form-select-option value="title">제목</b-form-select-option>
        <b-form-select-option value="content">내용</b-form-select-option>
        <b-form-select-option value="titleNContent"
          >제목+내용</b-form-select-option
        >
      </b-form-select>
    </b-col>
    <b-col cols="7">
      <b-form-input
        :value="word"
        @keypress.enter="sendKeyword"
        v-model="text"
      />
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "BoardSearch",
  data() {
    return {
      selectedKey: null,
      text: "",
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false
      }
    };
  },
  computed: {
    key() {
      return this.$store.getters.key;
    },
    word() {
      return this.$store.getters.word;
    }
  },
  created() {
    this.selectedKey = this.$store.getters.key;
    this.text = this.$store.getters.word;
  },
  methods: {
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
    sendKeyword() {
      if (!this.selectedKey) {
        this.notifyVue("검색 실패!", "검색 키워드를 선택해주세요!");
      } else {
        this.$store.dispatch("setKeyWord", {
          key: this.selectedKey,
          word: this.text
        });
        if (this.text != "") {
          this.$store.dispatch("searchBoard", {
            key: this.selectedKey,
            word: this.text
          });
        } else {
          this.$store.dispatch("getBoardsList", { url: "/board" });
        }
      }
    }
  }
};
</script>

<style></style>

<template>
  <div class="mt-3">
      <b-pagination
        v-model="currentPage"
        :total-rows="cnt"
        per-page="10"
        first-number
        last-number
        @page-click="clickPage"
        :value="page"
      >
      </b-pagination>
    </div>
</template>

<script>
  export default {
    name: 'Pagenation',
    data() {
      return {
        currentPage: this.$store.getters.page,
      };
    },
    updated() {
      this.currentPage = this.$store.getters.page;
    },
    computed: {
      page() {
        return this.$store.getters.page;
      }
    },
    props: ['cnt', 'URL', 'dispatchName'],
    methods: {
      clickPage(event, page) {
        event;
        let key = this.$store.getters.key;
        let word = this.$store.getters.word;
        console.log(key, word);
        console.log("여기 들어옴");
        if(key != null && word != '') {
          console.log(this.URL+'/'+key+'/'+word)
          this.$store.dispatch(this.dispatchName, {url: this.URL+'/'+key+'/'+word, page: page});
        } else {
          console.log(this.URL+'/'+page);
          this.$store.dispatch(this.dispatchName, {url: this.URL, page: page});
        }
      },
    },
  }
</script>
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
        align="center"
      >
      </b-pagination>
    </div>
</template>

<script>
  export default {
    name: 'Pagenation',
    data() {
      return {
        currentPage: this.$store.getters.aptpage,
      };
    },
    computed: {
      page() {
        return this.$store.getters.aptpage;
      }
    },
    updated() {
        this.currentPage = this.$store.getters.aptpage;
    },
    props: ['cnt', 'URL', 'dispatchName'],
    methods: {
      clickPage(event, page) {
        event;
        console.log(page);
        let key = this.$store.getters.aptkey;
        let word = this.$store.getters.aptword;
        console.log(key);
        console.log(word);
        this.$store.dispatch(this.dispatchName, {url: this.URL, key: key, word: word, page: page});
      },
    },
  }
</script>
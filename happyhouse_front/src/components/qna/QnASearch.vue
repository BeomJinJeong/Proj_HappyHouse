<template>
  <b-row class="mt-4 mb-4">
    <b-col class="sm-3" align="center">
      <b-form-input
        v-model.trim="title"
        placeholder="질문 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      />
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col>
  </b-row>
</template>

<script>
export default {
    name: 'QnASearch',
    data() {
        return{
            title: '',
        };
    },
    methods:{
        sendKeyword(){
          if(this.title != '')
              this.$store.dispatch('searchTitle', this.title);
            else
              this.$store.dispatch('getQuesList');
        }
    },
}
</script>

<style>

</style>
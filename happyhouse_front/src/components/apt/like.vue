<template>
  <div>
      <a href="#" @click="like()">좋아요</a>
  </div>
</template>

<script>
import http from '@/util/http-common';
export default {
    name: 'like',
    data(){
        return{
            aptno: this.apt.no,
        }
    },
    props:{
        apt: Object,
    },
    methods:{
        like(){
            console.log("clicked");
            console.log(this.apt);
            // this.$store.dispatch('like', this.no);
            http.get('/apt/like/' + this.aptno);

            console.log(this.$store.state.userid);
            console.log(this.aptno);
            http.get('/user/like/' + this.$store.state.userid + '/' + this.aptno);
        }
    },
}
</script>

<style scoped>

</style>
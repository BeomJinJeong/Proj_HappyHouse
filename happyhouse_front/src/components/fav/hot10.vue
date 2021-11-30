<template>
  <div>
      <h2>인기매물 TOP10!</h2>
      <div v-for="(apt, index) in apts" :key="index">
          {{apt.aptName}}
      </div>
  </div>
</template>

<script>
import http from '@/util/http-common';
export default {
    name: 'hot10',
    data(){
        return{
            apts:[],
        }
    },
    created(){
        http
            .get('/apt/hot10')
            .then(response=>{
                this.apts = response.data.list;
            })
            .catch((error)=>{
                console.dir(error);
            })
    },
    methods:{

    },
}
</script>

<style>

</style>
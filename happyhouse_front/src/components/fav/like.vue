<template>
  <div>
        <div @click="like()">
            <vue-clap-button icon="love" ref="clapBtn"/>
        </div>
  </div>
</template>

<script>
import http from '@/util/http-common';
// import vueClapButton from 'vue-clap-button'
export default {
    name: 'like',
    data(){
        return{
            aptno: this.apt.no,
            userId:'',
        }
    },
    Components:{
        // vueClapButton,
    },
    props:{
        apt: Object,
    },
    mounted(){
        // this.$refs.clapBtn.clap();
        // document.getElementById("clap-btn")[0].clap();
        // $("#clap-btn").trigger("clap");

        if(JSON.parse(localStorage.getItem('user'))==null){
                this.$refs.clapBtn.click();
        } else {
            this.userId = JSON.parse(localStorage.getItem('user'))['user-id'];
            http
                .get('/fav/selectLike/' + this.userId + '/' + this.aptno)
                .then(response=>{
                    if(response.data.isExist){
                        this.$refs.clapBtn.click();
                    }
                })
                .catch((error) => {
                    console.dir(error);
                });
        }
    },
    methods:{
        like(){
            if(JSON.parse(localStorage.getItem('user'))==null){
                alert("로그인 후 이용해주세요!")
            }else{
                this.userId = JSON.parse(localStorage.getItem('user'))['user-id'];
                console.log('Like!! /' + this.userId + '/' + this.aptno);
                http.get('/fav/like/' + this.userId + '/' + this.aptno);
            }
        },
    },
}
</script>

<style scoped>

</style>
<template>
  <div>
      {{hospital.hospital_name}}
      {{hospital.hospital_address}}
  </div>
</template>

<script>
// import {mapActions} from 'vuex';

export default {
    name: 'hospitalItem',
    data() {
        return{

        }
    },
    props:{
        hospital : Object,
    },
    methods: {
        // ...mapActions(['selectQues']),
        // selectQues(no){
        //     console.log(no);
        //     this.$store.dispatch('selectQuestion', no);
        //     this.$router.push('/detail');
        // }
    },
}
</script>

<style>

</style>
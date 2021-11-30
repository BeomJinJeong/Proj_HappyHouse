<template>
      <!-- <hospital-item v-for="(hospital, index) in hospitals" :key="index" :hospital="hospital"></hospital-item> -->
    <page :hospitals="hospitals" v-if="hospitals"></page>
    <div v-else> 안심병원이 존재하지 않습니다. </div>
</template>

<script>
// import HospitalItem from '@/components/hospital/HospitalItem.vue';
import Page from '@/components/pagination/Page.vue';
export default {
    name: 'hospitalList',
    components:{
        // HospitalItem,
        Page,
    },
    created(){
        this.$store.dispatch('getHospitalList');
    },
    computed: {
        hospitals(){
            console.log(this.$store.getters.hospitals);
            return this.$store.getters.hospitals;
        }
    },
    methods: {

    },
}
</script>

<style>

</style>
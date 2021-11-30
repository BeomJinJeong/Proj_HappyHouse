<template>
  <div class="bv-container-fluid">
      <div class="m-5"></div>
      <h4 class="ml-4">안심병원 검색</h4><hr>
      <hospital-search></hospital-search><hr>
      <hospital-list></hospital-list>
  </div>
</template>

<script>
import HospitalList from '@/components/hospital/HospitalList.vue';
import HospitalSearch from '@/components/hospital/HospitalSearch.vue'
export default {
    name: 'hospital',
    components: {
        HospitalSearch,
        HospitalList,
    },
    methods:{

    },
}
</script>

<style>

</style>
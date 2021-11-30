<template>
  <div>
    <b-card>
    <b-table striped id="my-table" :items="hospitals" :fields="fields" :per-page="perPage" :current-page="currentPage"></b-table>
    </b-card>

    <!-- <p class="mt-3">Current Page: {{ currentPage }}</p> -->
    <b-row>
    <b-col></b-col>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      style="text-align: center;"
    ></b-pagination>
    <b-col></b-col>
    </b-row>

  </div>
</template>

<script>
  export default {
    name: 'Page',
    props:{
        hospitals : Array,
    },
    data() {
      return {
          perPage: 10,
          currentPage: 1,
          fields: [
          { key: 'hospital_id', label: '번호' },
          { key: 'sido', label: '시/군' },
          { key: 'gungu', label: '구' },
          { key: 'hospital_name', label: '병원명' },
          { key: 'hospital_address', label: '주소' },
          { key: 'htype', label: '병원타입' },
          { key: 'hcall', label: '전화번호' }
        ]
      }
    },
    computed: {
      rows() {
        return this.hospitals.length;
      }
    },
  }
</script>
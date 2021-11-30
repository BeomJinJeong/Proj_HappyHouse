<template>
  <b-table class="table-striped" :items="aptList">
    <thead>
      <slot name="columns">
        <tr>
          <th v-for="column in columns" :key="column">{{column}}</th>
        </tr>
      </slot>
    </thead>
    <tbody>
      <!-- <slot :row="item"> -->
        <!-- <apt-item v-for="(apt, index) in aptList" :key="index" :apt="apt">
        </apt-item> -->
        <!-- <td v-for="column in columns" :key="column"></td> -->
      <!-- </slot> -->
    </tbody>
  </b-table>
</template>
<script>
  import AptItem from '@/components/apt/aptItem.vue';
  export default {
    name: 'l-table',
    props: {
      columns: Array,
      data: Array
    },
    components: {
      AptItem,
    },
    methods: {
      hasValue (item, column) {
        return item[column.toLowerCase()] !== 'undefined'
      },
      itemValue (item, column) {
        return item[column.toLowerCase()]
      }
    },
    computed: {
      aptList(){
            return this.$store.getters.aptList;
        },
    }
  }
</script>
<style>
</style>

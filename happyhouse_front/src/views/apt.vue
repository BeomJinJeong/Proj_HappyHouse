<template>
<div>
    <div><apt-list></apt-list></div>
</div>
</template>

<script>
import AptList from "@/components/apt/aptList.vue";
export default {
    components: {
        AptList,
    },
    created(){
        if(this.$store.state.flag != 'apt'){
            this.$store.state.aptWord = '';
            this.$store.state.aptKey = 'aptName';
            this.$store.state.aptPage = 1;
        }
    }
}
</script>
    
<style>

</style>
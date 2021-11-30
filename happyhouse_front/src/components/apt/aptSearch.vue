<template>
    <div>
        <b-row>
            <b-col></b-col>
            <b-col cols="2">
            <b-form-select v-model="key" :options="options"></b-form-select>
            </b-col>
            <b-col cols="4">
            <b-input v-model="searchText" @keyup.enter="searchApt"/>
            </b-col>
            <b-button @click="searchApt">검색</b-button>
            <b-col></b-col>
        </b-row>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            searchText: "",
            options: [
                { value: 'aptName', text: '아파트 이름' },
                { value: 'dong', text: '동' },
            ],
            key: "aptName",
            aptName: "",
        }
    },
    methods: {
        searchApt(){
            if(this.searchText != ''){
                console.log(this.$store.state.aptPage);
                this.$store.dispatch('getAptList', {url: 'apt/list', key: this.key, word: this.searchText, page: this.$store.getters.aptpage});
            } else {
                this.$store.dispatch('getAptList', {url: 'apt/list', key: 'aptName', word: '', page: 1});
            }
        }
    },
    created(){
        this.key = this.$store.state.aptKey;
        this.searchText = this.$store.state.aptWord;
    }
}
</script>

<style>
</style>
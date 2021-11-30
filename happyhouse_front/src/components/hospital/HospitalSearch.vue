<template>
<b-row>
    <b-col></b-col>
    <b-col cols="2">
        <b-form>
            <b-form-select :value="key" class="mb-3" v-model="key">
                <b-form-select-option :value="''" selected disabled hidden >검색 키워드를 선택해주세요.</b-form-select-option>
                <b-form-select-option value="hospital_name" >병원명</b-form-select-option>
                <b-form-select-option value="gungu">지역</b-form-select-option>
            </b-form-select>
        </b-form>
    </b-col>
    <b-col cols="4">
        <b-form>
            <b-input type="text" 
                    style="text-align: center;" 
                    v-model.trim="word" 
                    @keypress.enter.prevent="doSearch"
                    />
        </b-form>
    </b-col>
    <b-col>
        <b-button @click="doSearch">search</b-button>
    </b-col>
</b-row>
</template>

<script>
export default {
    name: 'hospitalSearch',
    data(){
        return{
            key:'',
            word:'',
        }
    },
    methods:{
        doSearch(){
            console.log('hospitalSearch Call');
            // console.log('key:' + this.key);
            // console.log('word:' + this.word);
            if(this.word=='') this.$store.dispatch('getHospitalList');
            else{
                const searchItem = {
                    key: this.key,
                word: this.word,
            };
            console.log('Itemkey:' + searchItem.key);
            console.log('Itemword:' + searchItem.word);
            this.$store.dispatch('searchHospital', searchItem);
            this.key = '';
                }
        }
    },
}
</script>

<style>

</style>
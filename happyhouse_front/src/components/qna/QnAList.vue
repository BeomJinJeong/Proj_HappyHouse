<template>
<div>
    <div>
        <b-container v-if="questions && questions.length != 0" class="bv-example-row mt-3">
            <qn-a-item v-for="(question, index) in questions" :key="index" :question="question" />
        </b-container>
        <b-container v-else class="bv-example-row mt-3">
            <b-row>
            <b-col><b-alert show>질문 목록이 없습니다.</b-alert></b-col>
            </b-row>
        </b-container>
        <b-button @click="mvRegistQuestion">질문 등록</b-button>
    </div>
</div>
</template>
<script>
// import { mapState } from 'vuex';
import QnAItem from '@/components/qna/QnAItem.vue';

export default {
    name: 'QnAList',
    components: {
        QnAItem,
    },
    created(){
        this.$store.dispatch('getQuesList');
    },
    computed : {
        questions(){
            return this.$store.getters.questions;
        }
    },
    methods:{
        selectQuestion(){
            this.$store.dispatch("selectQuestion");
        },
        mvRegistQuestion(){
            this.$router.push("/insert");
        }
    }
};

</script>

<style>

</style>
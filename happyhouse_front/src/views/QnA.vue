<template>
    <b-container class="bv-example-row">
        <div>
            <h1>QnA 게시판</h1>
            <b-row>
                <qn-a-search></qn-a-search>
            </b-row>
            <b-row>
                <qn-a-list> </qn-a-list>
            </b-row>
        </div>
    </b-container>
</template>

<script>
import QnAList from '@/components/qna/QnAList.vue';
import QnASearch from '@/components/qna/QnASearch.vue';

export default {
    name: 'QnA',
    components: {
        QnAList,
        QnASearch,
    },
    methods: {

    },
}
</script>
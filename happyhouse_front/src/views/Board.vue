<template>
    <b-container-fluid class="bv-example-row">
        <div>
            <b-row style="margin-right: 0;">
                <b-col><board-list> </board-list></b-col>
            </b-row>
        </div>
    </b-container-fluid>
</template>

<script>
import BoardList from '@/components/board/BoardList.vue';
import BoardSearch from '@/components/board/BoardSearch.vue';

export default {
    name: 'Board',
    created() {
        if(this.$store.state.flag != 'board'){
            this.$store.state.word = '';
            this.$store.state.key = null;
            this.$store.state.page = 1;
        }
    },
    components: {
        BoardList,
        BoardSearch,
    },
    methods: {

    },
}
</script>
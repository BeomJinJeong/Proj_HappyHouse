<template>
    <b-container class="bv-example-row mt-3">
        <b-row>
        <b-col><h1>질문 등록</h1></b-col><hr><br>
        </b-row>
        <b-row>
            <b-form>
                <label for="title">제목</label>
                <b-input id="title" type="text" v-model="title"></b-input>
                <label for="title">내용</label>
                <b-textarea id="title" rows="10" v-model="content"></b-textarea>
                <b-button style="margin:10px;" @click="insert">등록</b-button>
            </b-form>
        </b-row>
    </b-container>
</template>

<script>
export default {
    name: 'QnAInsert',
    data(){
        return{
            title:'',
            content:'',
        };
    },
    methods:{
        insert(){
            const question = {
                title: this.title,
                content: this.content,
            };
            if(this.title != ''){
                this.$store.dispatch('insertQuestion', question);
                this.$router.push('/');
            }
            else
                alert('제목 입력!');
        }
    },
}
</script>

<style>

</style>
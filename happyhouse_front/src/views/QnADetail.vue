<template>
  <b-container v-if="question" class="bv-example-row">
    <b-row>
      <b-col class="text">
        <h3>글번호</h3>
      </b-col>
      <b-col class="cont">
        <h3>{{ question.no }}</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col class="text">
        <h3>제목</h3>
      </b-col>
      <b-col class="cont">
        <h3>{{ question.title }}</h3>
      </b-col>
      </b-row>
    <b-row>
      <b-col class="text">
        <h3>내용</h3>
      </b-col>
      <b-col class="cont">
        <h3>{{ question.content }}</h3>
      </b-col>
      </b-row>
    <b-row>
      <b-col class="text">
        <h3>작성자 ID</h3>
      </b-col>
      <b-col class="cont">
        <h3>{{ question.userid }}</h3>
      </b-col>
    </b-row>
    <hr>
    <div v-for="answer in ans" :key="answer.no">
      {{answer.content}}
      <b-button @click="deleteAnswer(answer.no)">삭제</b-button>
      <hr>
    </div>
    <b-textarea v-model="answerText"></b-textarea>
    <b-button @click="addAnswer(question.no)">답변 등록하기</b-button>
  </b-container>
</template>

<script>
export default {
    name: 'QnADetail',
    data: function(){
      return {
        answerText: "",
      }
    },
    computed: {
      question() { 
        return this.$store.getters.question; 
      },
      ans() {
        return this.$store.getters.answers;
      }
    },
    methods: {
      addAnswer(no){
        console.log(no);
        let ans = {
          quesno : no,
          content : this.answerText,
        }
        this.$store.dispatch('addAnswer', ans);
      },
      deleteAnswer(no){
        console.log("hi");
        this.$store.dispatch('deleteAnswer', no);
      }
    },
}
</script>

<style>
.text {
  text-align: right;
}
.cont {
  text-align: left;
}
</style>
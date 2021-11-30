<template>
  <div>
  <b-row
    class="m-2"
    @click="selectQues(question.no)"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  > 
    {{question.title}}
    {{question.contents}}
  </b-row>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
    name: 'QnAItem',
    data() {
        return {
            isColor: false,
        };
    },
    props: {
        question: Object,
    },
    methods: {
        ...mapActions(['selectQues']),
        colorChange(flag) {
        this.isColor = flag;
        },
        selectQues(no){
            console.log(no);
            this.$store.dispatch('selectQuestion', no);
            this.$router.push('/detail');
        }
    },
}
</script>

<style scoped>
/* .img-list {
  width: 50px;
} */
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
<template>
  <div class="content" style="padding: 0; margin-left: -15px; background-size: cover;" :style="{backgroundImage: 'url(' + this.img + ')'}">
    <kinesis-container class="container">
      <kinesis-element  
                  id="B"
                 :strength="-10"
                  transformOrigin="100% 100%"
                  display="inline-block"
                  >
        <img src="@/assets/img/kine/B-1.png" alt="B logo">
      </kinesis-element>
      <kinesis-element  
                  id="E"
                 type="rotate"
                 :strength="15"
                 transformOrigin="50% 50%"
                  display="inline-block"
                  >
        <img src="@/assets/img/kine/e-1.png" alt="e logo">
      </kinesis-element>
      <kinesis-element  
                  id="A"
                 :strength="-15"
                  type="depth"
                  transformOrigin="100% 100%"
                  display="inline-block"
                  >
        <img src="@/assets/img/kine/a-1.png" alt="a logo">
      </kinesis-element>
      <kinesis-element  
                  id="N"
                 :strength="-15"
                  type="rotate"
                  transformOrigin="100% 100%"
                  display="inline-block"
                  >
        <img src="@/assets/img/kine/n-1.png" alt="n logo">
      </kinesis-element>
      <kinesis-element  
                  id="Room"
                 :strength="20"
                  type="depth"
                  display="inline-block"
                  >
        <img src="@/assets/img/kine/방-1.png" alt="room logo">
      </kinesis-element>


      <kinesis-element :strength="5">
        <div class="circle circle-purple2"></div>
      </kinesis-element>
      <kinesis-element :strength="5">
        <div class="circle circle-purple3"></div>
      </kinesis-element>
     <kinesis-element :strength="5">
        <div class="circle circle-purple1"></div>
      </kinesis-element>
      <kinesis-element :strength="5">
        <div class="circle circle-purple2"></div>
      </kinesis-element>
      <kinesis-element :strength="5">
        <div class="circle circle-purple3"></div>
      </kinesis-element>
    

    </kinesis-container>
  </div>
</template>

<script>
export default {
  name: "kinesis",
  data(){
      return{
          img : 'http://ojsfile.ohmynews.com/STD_IMG_FILE/2008/0629/IE000932779_STD.jpg',
      }
  },
  components: {
  },
};
</script>

<style>
#B {
   position: absolute;
    left: 42%;
    top: 43%;
}
#E {
  position: absolute;
    left: 46%;
    top: 41%;
}
#A {
  position: absolute;
    left: 50%;
    top: 41%;
}
#N {
  position: absolute;
    left: 54%;
    top: 43%;
}
#Room {
  position: absolute;
    left: 59%;
    top: 41%;
}

img {
  width: 4rem;
}

.container {
  margin: 0 0;
  width:100%;
  height:100%;
  position:absolute;
  /* top:5%;
  left:14%; */
  text-align:center;
}

@media (min-width: 1200px){
    .container, .container-lg, .container-md, .container-sm, .container-xl {
        max-width: 100% !important;
    }
}
@media (min-width: 1200px) {
    .container, .container-sm, .container-md, .container-lg, .container-xl{
        max-width: 100% !important;
        width: 100% !important;
    }
}
@media screen and (min-width: 992px) {
    .container, .container-sm, .container-md, .container-lg{
        max-width: 100% !important;
        width: 100% !important;
    }
}
@media (min-width: 768px){}
    .container, .container-sm, .container-md, .container-lg {
        max-width: 100% !important;
        width: 100% !important;
}
@media (min-width: 576px){
    .container, .container-sm {
        max-width: 100% !important;
        width: 100% !important;
    }
}
/* .circle {
  position: absolute;
  border-radius: 100%;
} */

/* .circle.circle-white {
  background-color: #ffffff;
  text-align: center;
  opacity: 1;
  width: 10vw;
  height: 10vw;
  left: 50%;
  top: 50%;
}

.circle.circle-purple {
  border: 10px solid #f2eafa;
  opacity: 0.1;
  width: 4vw;
  height: 4vw;
  left: 10%;
  top: 25%;
}

.circle.circle-purple1 {
  border: 15px solid #f2eafa;
  opacity: 0.1;
  width: 8vw;
  height: 8vw;
  right: -2%;
  bottom: 17%;
}
.circle.circle-purple2 {
  background-color: #f2eafa;
  opacity: 0.1;
  width: 5vw;
  height: 5vw;
  left: 20%;
  bottom: 17%;
}

.circle.circle-purple3 {
  border: 15px solid #f2eafa;
  opacity: 0.3;
  width: 3vw;
  height: 3vw;
  top: 80%;
  left: 60%;
} */
</style>

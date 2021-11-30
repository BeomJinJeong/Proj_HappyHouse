<template>
  
  <card>
    <h4 slot="header" class="card-title">SignUp</h4>
    <form>
      <div class="row">
        <div class="col-md-3">
          <base-input type="text"
                    label="UserId"
                    placeholder="Userid"
                    v-model="user.userid">
          </base-input>
        </div>
        <div class="col-md-3">
          <base-input type="password"
                    label="PASSWORD"
                    placeholder="password"
                    v-model="user.userpwd">
          </base-input>
        </div>
        <div class="col-md-3">
          <base-input type="text"
                    label="Username"
                    placeholder="Username"
                    v-model="user.username">
          </base-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-3">
          <base-input type="text"
                    label="GRADE"
                    placeholder="user"
                    :value="user.grade"
                    readonly>
          </base-input>
        </div>
        <div class="col-md-3">
          <base-input type="text"
                    label="PHONE"
                    placeholder="000-0000-0000"
                    v-model="user.phone">
          </base-input>
        </div>
        <div class="col-md-4">
          <base-input type="email"
                    label="Email"
                    placeholder="Email"
                    v-model="user.email">
          </base-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-5">
          <base-input type="text"
                    label="Address"
                    placeholder="Home Address"
                    v-model="user.jibunAdd"
                    @click="sample4_execDaumPostcode"
                    readonly
                    >
          </base-input>
        </div>
        <div class="col-md-5">
          <base-input type="text"
                    label="Address"
                    placeholder="Home Address"
                    v-model="user.roadAdd"
                    @click="sample4_execDaumPostcode"
                    readonly
                    >
          </base-input>
        </div>
        <div class="col-md-2">
          <base-input type="text"
                    label="Rest Address"
                    placeholder="Home Address"
                    v-model="user.restAdd">
          </base-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <div class="form-group">
            <label>About Me</label>
            <textarea rows="5" class="form-control border-input"
                      placeholder="Here can be your description"
                      v-model="user.aboutMe">
              </textarea>
          </div>
        </div>
      </div>
      <div class="text-center">
        <button type="submit" class="btn btn-info btn-fill float-right" @click.prevent="registProfile">
          Regist Profile
        </button>
      </div>
      <div class="clearfix"></div>
    </form>
  </card>
</template>
<script>
  import Card from 'src/components/Cards/Card.vue'
  let daum = '';

  export default {
    components: {
      Card
    },
    data () {
      return {
        user: {
          userid: '',
          userpwd: '',
          username: '',
          email: '',
          grade: 'user',
          phone: '',
          jibunAdd: '',
          roadAdd: '',
          aboutMe: ``,
          restAdd: '',
        }
      }
    },
    methods: {
      registProfile () {
        // alert('Your data: ' + JSON.stringify(this.user))
        this.$store.dispatch('registMember', this.user);
        if(localStorage.getItem("user") != null){
          alert('회원가입이 완료되었습니다.');
          this.$router.push('/');
        } else {
          alert('회원가입에 오류가 발생했습니다. 다시 시도해주십시오.');
          this.$$router.push('#/signup');
        }
      },
      sample4_execDaumPostcode(){
        let t = this;
        new window.daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                // t.user.address = "(" +  data.zonecode + ") ";
                t.user.roadAdd = roadAddr;
                t.user.jibunAdd = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                // if(roadAddr !== ''){
                //     this.user.address += " " + extraRoadAddr;
                // } else {
                //     this.user.address += '';
                // }

                // var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                // if(data.autoRoadAddress) {
                //     var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                //     guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                //     guideTextBox.style.display = 'block';

                // } else if(data.autoJibunAddress) {
                //     var expJibunAddr = data.autoJibunAddress;
                //     guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                //     guideTextBox.style.display = 'block';
                // } else {
                //     guideTextBox.innerHTML = '';
                //     guideTextBox.style.display = 'none';
                // }
            }
        }).open();
      }
    },
  }

</script>
<style>

</style>

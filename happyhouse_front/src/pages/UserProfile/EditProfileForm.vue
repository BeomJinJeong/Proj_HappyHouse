<template>
  <card>
    <h4 slot="header" class="card-title">Edit Profile</h4>
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
          <base-input type="text"
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
        <button type="submit" class="btn btn-info btn-fill float-right" @click.prevent="updateProfile">
          Update Profile
        </button>
      </div>
      <div class="clearfix"></div>
    </form>
  </card>
</template>
<script>
  import Card from 'src/components/Cards/Card.vue'
  import axios from 'axios';
  const SERVER_URL = process.env.VUE_APP_SERVER_URL;
  export default {
    components: {
      Card
    },
    data () {
      return {
        aboutMe: '',
        user: {
            userid: '',
            userpwd: '',
            username: '',
            email: '',
            jibunAdd: '',
            roadAdd: '',
            restAdd: '',
            userphone: '',
            grade: '',
            aboutMe: '',
          },
          type: ['', 'info', 'success', 'warning', 'danger'],
      }
    },
    methods: {
      updateProfile () {
        alert('Your data: ' + JSON.stringify(this.user))
        console.log(this.user);
      },
      sample4_execDaumPostcode(){
        let t = this;
        new window.daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                t.user.roadAdd = roadAddr;
                t.user.jibunAdd = data.jibunAddress;
            }
        }).open();
      },
      updateProfile(){
        this.$store.dispatch('updateProfile', this.user);
        this.$router.push('/');
      },
      notifyVue (verticalAlign, horizontalAlign) {
        const color = Math.floor((Math.random() * 4) + 1)
        this.$notifications.notify(
          {
            message: `<span>앗 <b>로그인이 되어있지 않아요</b> <br> 로그인 후 가능한 서비스입니다.</span>`,
            icon: 'nc-icon nc-app',
            horizontalAlign: horizontalAlign,
            verticalAlign: verticalAlign,
            type: this.type[color]
          })
      }
    },
    created(){
      axios
      .post(`${SERVER_URL}/user/mvEdit`)
      .catch(() => {
        this.notifyVue('top', 'center');
        // alert("로그인 후 이용 가능합니다.");
        this.$store.dispatch('LOGOUT');
        this.$router.push('/');
      })
      this.user = this.$store.getters.getUser;
    }
  }

</script>
<style>

</style>

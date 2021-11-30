import Vue from "vue";
import Vuex from "vuex";
import http from '@/util/http-common';
import axios from 'axios';

Vue.use(Vuex);
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default new Vuex.Store({
  state: {
    flag: 'board',
    aptList: [],
    apt: {},
    hospital: {},
    hospitals: [],
    question: {},
    questions: [],
    answers: [],
    boards: [],
    board: {},
    boardPr: {},
    key: null,
    word: '',
    page: 1,
    aptPage: 1,
    aptKey: 'aptName',
    aptWord: '',
    aptPr: '',
    aptInfos: [],
    selectedAreaAptDeals: [],
    accessToken: null,
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
    hot10List:[],
  },
  getters: {
    hospital : state => state.hospital,
    hospitals: state => state.hospitals,
    aptList: state => state.aptList,
    apt: state => state.apt,
    questions: state => state.questions,
    question: state => state.question,
    answers: state => state.answers,
    boards: state => state.boards,
    board: state => state.board,
    bpr: state => state.boardPr,
    key: state => state.key,
    word: state => state.word,
    page: state => state.page,
    aptkey: state => state.aptKey,
    aptword: state => state.aptWord,
    aptpage: state => state.aptPage,
    aptPr: state => state.aptPr,
    aptInfos: state => state.aptInfos,
    selectedAreaAptDeals: state => state.selectedAreaAptDeals,
    getAccessToken(state) {
      return state.accessToken;
    },
    getUser: state => state.user,
  },
  mutations: {
    GET_HOSPITAL_LIST(state, data) {
      state.hospitals = data.list;
    },
    GET_QUES_LIST(state, list) {
      state.questions = list;
    },
    GET_BOARDS_LIST(state, data) {
      state.boards = data.boardList;
      state.boardPr = data.pageResult;
      state.page = data.pageResult.pageNo;
    },
    SELECT_QUES(state, data) {
      state.question = data.qnaInfo;
      state.answers = data.qnaAnswerList;
    },
    SELECT_BOARD(state, data) {
      state.board = data;
    },
    SEARCH_QUES(state, list) {
      state.questions = list;
    },
    SEARCH_BOARDS(state, data) {
      state.boards = data.boardList;
      state.boardPr = data.pageResult;
    },
    addAns(state, data) {
      state.answers = data;
    },
    deleteAnswer(state, data) {
      state.answers = data;
    },
    SET_KEY_WORD(state, obj) {
      state.key = obj.key;
      state.word = obj.word;
      state.page = 1;
    },
    searchAptList(state, data) {
      state.aptList = data.list;
    },
    getAptList(state, data) {
      state.aptList = data.data.aptlist;
      state.aptKey = data.obj.key;
      state.aptWord = data.obj.word;
      state.aptPage = data.obj.page;
      state.aptPr = data.data.pageResult;
    },
    selectApt(state, data) {
      state.apt = data;
    },
    APT_INFO_LIST(state, data) {
      state.aptInfos = data;
    },
    APT_INFO_LIST(state, data) {
      state.aptInfos = data;
    },
    RESET_SELECTED_AREA_APT_DEALS(state) {
      state.selectedAreaAptDeals = [];
    },
    SET_SELECTED_AREA_APT_DEALS(state, data) {
      state.selectedAreaAptDeals = data;
    },
    LOGIN(state, payload) {
      state.accessToken = payload['auth-token'];
      state.user.userid = payload['user-id'];
      state.user.username = payload['user-name'];
      state.user.userpwd = payload['user-pwd'];
      state.user.email = payload['user-email'];
      state.user.jibunAdd = payload['user-jibunAdd'];
      state.user.roadAdd = payload['user-roadAdd'];
      state.user.restAdd = payload['user-restAdd'];
      state.user.userphone = payload['user-phone'];
      state.user.grade = payload['user-grade'];
      state.user.aboutMe = payload['user-aboutMe'];
      localStorage.setItem("user", JSON.stringify(payload));
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.user.userid = '';
      state.user.username = '';
      state.user.userpwd = '';
      state.user.email = '';
      state.user.jibunAdd = '';
      state.user.roadAdd = '';
      state.user.restAdd = '';
      state.user.userphone = '';
      state.user.grade = '';
      state.user.aboutMe = '';
    },
  },
  actions: {
    searchHospital({ commit }, searchItem) {
      http
        .get('/hospital/'+ searchItem.key + '/' + searchItem.word)
        .then((response) => {
          commit('GET_HOSPITAL_LIST', response.data);
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    getHospitalList({ commit }) {
      http
        .get('/hospital')
        .then((response) => {
          commit('GET_HOSPITAL_LIST',response.data);
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    getQuesList({ commit }) {
      http
        .get('/qna')
        .then((response) => {
          commit('GET_QUES_LIST', response.data.list);
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    getBoardsList({ commit }, obj) {
      let go = obj.url + '/';
      if (obj.page != undefined) {
        go += obj.page;
      }
      http
        .get(go)
        .then((response) => {
          // commit('GET_BOARDS_LIST', response.data.boardList);
          commit('GET_BOARDS_LIST', response.data);
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    selectQuestion({commit}, no) {
      http
        .get('/qna/detail/'+ no)
        .then((response) => {
          commit('SELECT_QUES', response.data);
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    selectBoard({ commit }, idx) {
      http
        .get('/board/detail/' + idx)
        .then((response) => {
          commit('SELECT_BOARD', response.data);
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    searchTitle({ commit }, text) {
      http
        .get('/qna/title/'+text)
        .then((response) => {
          commit('SEARCH_QUES', response.data.list);
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    searchBoard({ commit }, obj) {
      http
        .get('/board/' + obj.key + '/' + obj.word)
        .then((response) => {
          commit('SEARCH_BOARDS', response.data);
          commit('SET_KEY_WORD', obj);
        })
        .catch((error) => {
          console.dir(error)
        });
    },
    insertQuestion({ commit }, question) {
      http
      .post('/qna/register', question)
      .then((response) => {
        commit('GET_QUES_LIST', response.data.list);
      })
        .catch((error) => {
          console.dir(error);
        });
    },
    addAnswer({ commit }, ans) {
      http
        .post('/answer/register', ans)
        .then((response) => {
          commit('addAns', response.data);
      })
    },
    deleteAnswer({ commit }, no) {
      http
        .delete('/answer/delete/' + no)
        .then((response) => {
          commit('deleteAnswer', response.data);
      })
    },
    setKeyWord({ commit }, obj) {
      commit('SET_KEY_WORD', obj);
    },
    getAptList({ commit }, obj) {
      let a = '/' + obj.url + '?key=' + obj.key + '&word=' + obj.word + '&page=' + obj.page;
      http
        .get(a)
        .then((response) => {
          commit('getAptList', { data: response.data, obj: obj });
      })
    },
    selectApt({ commit }, no) {
      http
        .get('/apt/detail/' + no)
        .then((response) => {
          commit('selectApt', response.data);
      })
    },
    loadHouseInfo({ commit }) {
      http
        .get('/apt/infoList')
        .then((response) => {
          commit('APT_INFO_LIST', response.data);
        });
    },
    resetSelectedAreaAptDeals({ commit }) {
      commit('RESET_SELECTED_AREA_APT_DEALS');
    },
    setSelectedAreaAptDeals({ commit }, payload) {
      commit('SET_SELECTED_AREA_APT_DEALS', payload);
    },
    LOGIN(context, user) {
      return http
      .post(`/user/confirm/login`, user)
      // .post(`//localhost:8000/vue/user/confirm/login`, user)
      .then(({ data }) => {
          console.log(data);
          context.commit('LOGIN', data);
          axios.defaults.headers.common['auth-token'] = `${data['auth-token']}`;
        });
    },
    LOGOUT(context) {
      context.commit('LOGOUT');
      http.defaults.headers.common['auth-token'] = undefined;
      localStorage.removeItem('user');
      axios.defaults.headers.common['auth-token'] = '';
    },
    registMember(context, user) {
      // console.log(user);
      http
      .post('/user/regist', user)
      .then(({ data }) => {
        context.commit('LOGIN', data);
        axios.defaults.headers.common['auth-token'] = `${data['auth-token']}`;
      })
    },
    updateProfile(context, userDto) {
      console.log(userDto);
      axios
        .put(`${SERVER_URL}/user/updateProfile`, userDto)
        .then(({ data }) => {
          context.commit('LOGIN', data);
          axios.defaults.headers.common['auth-token'] = `${data['auth-token']}`;
        })
    }
  },
});

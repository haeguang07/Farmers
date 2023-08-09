import {
  createStore
} from 'vuex';
import axios from 'axios'

export default createStore({
  state: {
    dst1: [],
    regSttsList: [],
    warnSttsList:[] ,
    warnReasonList:[] ,
    des2All: {},
    allCode:{}
  },
  getters: {
    getDst1(state) {
      return state.dst1;
    },
    getRegSttsList(state) {
      return state.regSttsList;
    },
    getAllCode(state) {
      return state.allCode;
    },
    getDes2All(state) {
      return state.des2All;
    },
    getWarnSttsList(state) {
      return state.warnSttsList;
    },
    getWarnReasonList(state) {
      return state.warnReasonList;
    },


  },
  mutations: {
    setDst1(state, dst1) {
      state.dst1 = dst1;
    },
    setRegSttsList(state, regSttsList) {
      state.regSttsList = regSttsList;
    },
    setAllCode(state, allCode) {
      state.allCode = allCode;
    },
    setDes2All(state, des2All) {
      state.des2All = des2All;
    },
    setWarnSttsList(state, warnSttsList) {
      state.warnSttsList = warnSttsList;
    },
    setWarnReasonList(state, warnReasonList) {
      state.warnReasonList = warnReasonList;
    },
  },
  actions: {
    fetchCodes({ commit }) {
      axios.get('/admin/getCodes')
        .then(response => {
          const data = response.data;
          commit('setAllCode', data);
          commit('setDst1', data['0K']);
          commit('setRegSttsList', data['0E']);
          commit('setWarnReasonList', data['0Y']);
          commit('setWarnSttsList', data['0Z']);
          // 전체 데이터의 복사본
          const des2All = { ...data};
          // 복사본에서 지역2만 저장
          delete des2All['0K'];
          delete des2All['0E'];
          delete des2All['0Y'];
          delete des2All['0Z'];
          delete des2All['0C'];
          commit('setDes2All', des2All);
        })
        .catch(error => {
          console.error('There was an error fetching codes:', error);
        });
    }
  },
  modules: {}
})
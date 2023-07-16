const routes = [{
  path: '/',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/myPage/member.vue', options))
},
  {
  path: '/myBoardVue',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/myPage/myBoard.vue', options))
}
]

const router = VueRouter.createRouter({
history: VueRouter.createWebHashHistory(),
routes,
});
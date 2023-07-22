const routes = [{
  path: '/',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/notice.vue', options))
},
{
  path: '/community',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/community.vue', options))
},
{
  path: '/event',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/event.vue', options))
},
{
  path: '/event/:boardNo',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/eventInfo.vue', options))
}
,
{
  path: '/noticeInfo/:boardNo',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/noticeInfo.vue', options)),
  name : 'noticeInfo',
  props : true
}
]

const router = VueRouter.createRouter({
history: VueRouter.createWebHashHistory(),
routes,
});
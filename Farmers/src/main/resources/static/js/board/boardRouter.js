const routes = [{
  path: '/',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/notice/notice.vue', options))
},
{
  path: '/community',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/notice/community.vue', options))
},
{
  path: '/event',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/notice/event.vue', options))
},
{
  path: '/event/:boardNo',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/notice/eventInfo.vue', options))
}
,
{
  path: '/noticeInfo/:boardNo',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/notice/noticeInfo.vue', options)),
  name : 'noticeInfo',
  props : true
},
{
  path: '/addNotice',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/board/main/notice/addNotice.vue', options))
}
]

const router = VueRouter.createRouter({
history: VueRouter.createWebHashHistory(),
routes,
});
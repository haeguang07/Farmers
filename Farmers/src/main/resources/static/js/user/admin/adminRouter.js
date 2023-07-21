const routes = [{
    path: '/',
    alias: ['/memberManagement'],
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/memberManagement.vue', options))
  },
  {
    path: '/farmLend',
    //component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/farmLend.vue', options))
  },
  {
    path: '/experience',
    //component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/experience.vue', options))
  },
  {
    path: '/equipment',
    //component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/equipment.vue', options))
  },
  {
    path: '/funding',
    //component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/funding.vue', options))
  },
  {
    path: '/jobVacancy',
    //component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/jobVacancy.vue', options))
  },
  {
    path: '/market',
    //component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/market.vue', options))
  },
  {
    path: '/auction',
    //component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/auction.vue', options))
  },
  {
    path: '/farmbnb',
    //component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/myPage/farmbnb.vue', options))
  }
]

const router = VueRouter.createRouter({
  history: VueRouter.createWebHashHistory(),
  routes,
});
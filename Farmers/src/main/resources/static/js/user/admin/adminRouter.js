const routes = [{
    path: '/',
    alias: ['/memberManagement'],
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/memberManagement.vue', options))
  },
  {
    path: '/farmLend',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/farmLend.vue', options))
  },
  {
    path: '/experience',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/experience.vue', options))
  },
  {
    path: '/equipment',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/equipment.vue', options))
  },
  {
    path: '/funding',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/funding.vue', options))
  },
  {
    path: '/jobVacancy',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/jobVacancy.vue', options))
  },
  {
    path: '/market',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/market.vue', options))
  },
  {
    path: '/auction',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/auction.vue', options))
  },
  {
    path: '/farmbnb',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/farmbnb.vue', options))
  },
  {
    path: '/inquiryAdmin',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/inquiry.vue', options))
  },
  {
    path: '/report',
    component: Vue.defineAsyncComponent(() => loadModule('../Vue/user/admin/router/report.vue', options))
  }
]

const router = VueRouter.createRouter({
  history: VueRouter.createWebHashHistory(),
  routes,
});


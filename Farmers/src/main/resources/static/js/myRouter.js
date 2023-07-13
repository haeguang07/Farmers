const routes = [{
  path: '/',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/market/cart/n8Cart.vue', options))
},
{
  path: '/n2Cart',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/market/cart/n2Cart.vue', options))
},
{
  path: '/n7Cart',
  component: Vue.defineAsyncComponent(() => loadModule('../Vue/market/cart/n7Cart.vue', options))
}
]

const router = VueRouter.createRouter({
history: VueRouter.createWebHashHistory(),
routes,
});
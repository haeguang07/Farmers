import {
  createRouter,
  createWebHistory
} from 'vue-router'
import auction from '../views/auction.vue'

import experience from '../views/experience.vue'
import farmLend from '../views/farmLend.vue'
import funding from '../views/funding.vue'
import inquiry from '../views/inquiry.vue'
import market from '../views/market.vue'
import memberManagement from '../views/memberManagement.vue'
import farmbnb from '../views/farmbnb.vue'

const routes = [{
    path: '/',
    alias: ['/admin'],
    name: 'memberManagement',
    component: memberManagement
  },
  {
    path: '/admin/v/auction',
    name: 'auction',
    component: auction
  },{
    path: '/admin/v/funding',
    name: 'funding',
    component: funding
  },{
    path: '/admin/v/farmLend',
    name: 'farmLend',
    component: farmLend
  },{
    path: '/admin/v/experience',
    name: 'experience',
    component: experience
  },{
    path: '/admin/v/market',
    name: 'market',
    component: market
  },{
    path: '/admin/v/farmbnb',
    name: 'farmbnb',
    component: farmbnb
  },{
    path: '/admin/v/inquiry',
    name: 'inquiry',
    component: inquiry
  }


]




const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
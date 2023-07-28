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
import report from '../views/report.vue'
import farmbnb from '../views/farmbnb.vue'

const routes = [{
    path: '/member',
    name: 'memberManagement',
    component: memberManagement
  },
  {
    path: '/auction',
    name: 'auction',
    component: auction
  },{
    path: '/funding',
    name: 'funding',
    component: funding
  },{
    path: '/farmLend',
    name: 'farmLend',
    component: farmLend
  },{
    path: '/experience',
    name: 'experience',
    component: experience
  },{
    path: '/market',
    name: 'market',
    component: market
  },{
    path: '/farmbnb',
    name: 'farmbnb',
    component: farmbnb
  },{
    path: '/inquiry',
    name: 'inquiry',
    component: inquiry
  }, {
    path: '/report',
    name: 'report',
    component: report
  }


]




const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
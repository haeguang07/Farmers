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
    component: memberManagement,
    meta: {
      title : '회원관리'
    }
  },
  {
    path: '/admin/v/auction',
    name: 'auction',
    component: auction,
    meta: {
      title : '경매승인관리'
    }
  },{
    path: '/admin/v/funding',
    name: 'funding',
    component: funding,
    meta: {
      title : '펀딩승인관리'
    }
  },{
    path: '/admin/v/farmLend',
    name: 'farmLend',
    component: farmLend,
    meta: {
      title : '농지승인관리'
    }
  },{
    path: '/admin/v/experience',
    name: 'experience',
    component: experience,
    meta: {
      title : '체험승인관리'
    }
  },{
    path: '/admin/v/market',
    name: 'market',
    component: market,
    meta: {
      title : '마켓승인관리'
    }
  },{
    path: '/admin/v/farmbnb',
    name: 'farmbnb',
    component: farmbnb,
    meta: {
      title : '비엔비승인관리'
    }
  },{
    path: '/admin/v/inquiry',
    name: 'inquiry',
    component: inquiry,
    meta: {
      title : '문의관리'
    }
  }


]




const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
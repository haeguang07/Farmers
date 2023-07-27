import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import CKEditor from '@ckeditor/ckeditor5-vue';

import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

loadFonts()

const app=createApp(App)
  .use(router)
  .use(store)
  .use(vuetify)
  .use(CKEditor)
  .use(VueSweetalert2);

  store.dispatch('fetchCodes'); 
  
  app.mount('#app');


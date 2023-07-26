import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import CKEditor from '@ckeditor/ckeditor5-vue';


loadFonts()

createApp(App)
  .use(router)
  .use(store)
  .use(vuetify)
  .use(CKEditor)
  .mount('#app')


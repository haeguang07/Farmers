const {
  defineConfig
} = require('@vue/cli-service')
module.exports = defineConfig({
  

  pluginOptions: {
	  
    vuetify: {
			// https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
		}
  }
})
const target = 'http://54.180.74.135:85';

module.exports = {
  devServer: {
    proxy: target
  },
  indexPath : "admin.html",
  pluginOptions: {
    vuetify: {}
  }
}

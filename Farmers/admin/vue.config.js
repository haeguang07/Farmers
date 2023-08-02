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


module.exports = {
  chainWebpack: (config) => {
    config.resolve.symlinks(false)
  },	
  indexPath : "admin.html",
  pluginOptions: {
    vuetify: {}
  }
}

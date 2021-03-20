module.exports = {
// relative path for dev
  publicPath: process.env.NODE_ENV === 'production' ? '/edu_boss/' : './',
  // for gh-pages
  indexPath: 'index.html',
  assetsDir: 'static',
  lintOnSave: process.env.NODE_ENV !== 'production',
  productionSourceMap: false,
  css: {
    // sourceMap: process.env.NODE_ENV !== 'production'
  },
  devServer: {
    open: true,
    port: 8081
  }
}

import Vue from 'vue'
import VueMaterial from 'vue-material'
import { makeHot, reload } from './util/hot-reload'
import { createRouter } from './router'

Vue.use(VueMaterial)

const navbarComponent = () => import('./components/navbar').then(({ NavbarComponent }) => NavbarComponent)
// const navbarComponent = () => import(/* webpackChunkName: 'navbar' */'./components/navbar').then(({ NavbarComponent }) => NavbarComponent)

import './sass/main.scss'

if (process.env.ENV === 'development' && module.hot) {
  const navbarModuleId = './components/navbar'

  // first arguments for `module.hot.accept` and `require` methods have to be static strings
  // see https://github.com/webpack/webpack/issues/5668
  makeHot(navbarModuleId, navbarComponent,
    module.hot.accept('./components/navbar', () => reload(navbarModuleId, (require('./components/navbar') as any).NavbarComponent)))
}

// tslint:disable-next-line:no-unused-expression
new Vue({
  el: '#app-main',
  router: createRouter(),
  components: {
    'navbar': navbarComponent
  },
})

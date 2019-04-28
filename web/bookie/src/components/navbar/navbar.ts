import { Component, Vue, Watch } from 'vue-property-decorator'
import bCollapse from 'bootstrap-vue/es/components/collapse/collapse'
import bNavItem from 'bootstrap-vue/es/components/nav/nav-item'
import bNavbar from 'bootstrap-vue/es/components/navbar/navbar'
import bNavbarToggle from 'bootstrap-vue/es/components/navbar/navbar-toggle'
import bNavbarBrand from 'bootstrap-vue/es/components/navbar/navbar-brand'
import bNavbarNav from 'bootstrap-vue/es/components/navbar/navbar-nav'
import { Link } from './link'
import { Logger } from '../../util/log'
import './navbar.scss'
import HttpRequest from '../../axios/api.request'
// @ts-ignore
import global_ from '../../common/common'
@Component({
  template: require('./navbar.html'),
  components: {
    'b-collapse': bCollapse,
    'b-nav-item': bNavItem,
    'b-navbar': bNavbar,
    'b-navbar-toggle': bNavbarToggle,
    'b-navbar-brand': bNavbarBrand,
    'b-navbar-nav': bNavbarNav
  }
})
export class NavbarComponent extends Vue {

  object: { default: string } = { default: 'Default object property!' }
  links: Link[] = [
    new Link('浏览', '/'),
    new Link('详情', '/list'),
    new Link('我的', '/about'),
    new Link('注册', '/register')
  ]
  shouldDisplayDetails: boolean = global_.highlightBook !== undefined
  bookNames = []
  selectedBook = ''
  protected logger: Logger
  updateSelected () {
    HttpRequest.get('/search', {
      params: {
        q: this.selectedBook
      }
    }).then(response => {
      while (global_.selectedBooks.pop() !== undefined);
      while (this.bookNames.pop() !== undefined);
      if (response.data['status'] !== 'ok') {
        return
      }
      for (let i of response.data['data']) {
        global_.selectedBooks.push(i)
        this.bookNames.push(i.title)
        this.bookNames.push(i.author)
      }
      console.log(global_.selectedBooks)
    })
  }

  @Watch('$route.path')
  pathChanged () {
    this.logger.info('Changed current path to: ' + this.$route.path)
  }

  mounted () {
    if (!this.logger) this.logger = new Logger()
    this.$nextTick(() => this.logger.info(this.object.default))
  }

  enter () {
    console.log('Entered Navbar View')
  }
}

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
import { createRouter } from '../../router'
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
  bookNames = []
  selectedBook = ''
  protected logger: Logger
  updateSelected () {
    console.log("I'm here!")

    this.bookNames = []
    for (let i of global_.bookLibrary) {
      this.bookNames.push(i.bookName)
      this.bookNames.push(i.author)
      this.bookNames.sort()
    }

    while (global_.selectedBooks.pop() !== undefined);
    if (this.selectedBook === '') {
      // @ts-ignore
      let counter = 0
      for (let i of global_.bookLibrary) {
        counter++
        global_.selectedBooks.push(i)
        if (counter > global_.MAX_NUMBER) {
          break
        }
      }
    } else {
      console.log('Oh, this.selectedBook = ' + this.selectedBook)
      for (let i of global_.bookLibrary) {
        // console.log('i: ')
        // console.log(i)
        // console.log('Consider ' + i.bookName + ' contains ' + this.selectedBook)
        if (i.bookName.indexOf(this.selectedBook) >= 0) {
          global_.selectedBooks.push(i)
        } else if (i.author.indexOf(this.selectedBook) >= 0) {
          global_.selectedBooks.push(i)
        }
      }
    }
    console.log(global_.selectedBooks)
    // const router = createRouter()
    // router.go(0)
  }

  @Watch('$route.path')
  pathChanged () {
    this.logger.info('Changed current path to: ' + this.$route.path)
  }

  mounted () {
    if (!this.logger) this.logger = new Logger()
    this.$nextTick(() => this.logger.info(this.object.default))
  }
}

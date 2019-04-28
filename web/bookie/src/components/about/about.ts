import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'
import { Logger } from '../../util/log'
import HttpRequest from '../../axios/api.request'
@Component({
  template: require('./about.html'),
  'data': function () {
    return {
      cartList: [],
      purchasedList: []
    }
  },
  components: {
    'b-container': bContainer,
    'b-col': bCol,
    'b-row': bRow
  }
})
export class AboutComponent extends Vue {

  repo: string = 'https://github.com/ducksoupdev/vue-webpack-typescript'
  purchasedTextField: string = '您尚未有已购项目。'
  marketTextField: string = '您的购物车是空的。'
  protected logger: Logger

  getMetaInfo(isbn: string) {

  }
  mounted () {
    if (!this.logger) this.logger = new Logger()
    this.$nextTick(() => this.logger.info('about is ready!'))
    HttpRequest.get('/bill').then(response => {
      if (response['status'] === 200) {
        let resp = response['data']
        if (resp['status'] === 'ok') {
          this.$data.purchasedList = resp['data']
          if (this.$data.purchasedList.length !== 0) {
            this.purchasedTextField = '您有 ' + this.$data.purchasedList.length + ' 项已购项目。'
          } else {
            this.purchasedTextField = '您尚未有已购项目。'
          }
        } else {
          alert('读取历史订单失败。错误信息：' + resp['status'])
        }
      }
    })

    HttpRequest.get('/market').then(response => {
      if (response['status'] === 200) {
        let resp = response['data']
        if (resp['status'] === 'ok') {
          this.$data.cartList = resp['data']
          if (this.$data.cartList.length !== 0) {
            this.marketTextField = '您的购物车中有 ' + this.$data.cartList.length + ' 项目。'
          } else {
            this.marketTextField = '您的购物车是空的。'
          }
        } else {
          alert('读取购物车失败。错误信息：' + resp['status'])
        }
      }
    })
  }
}

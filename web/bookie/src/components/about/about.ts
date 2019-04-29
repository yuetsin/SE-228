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
      flush: true,
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
  buyFromMarket (uuid: string) {
    HttpRequest.post('/alter?uuid=' + uuid).then(response => {
      if (response['status'] === 200) {
        let resp = response['data']
        if (resp['status'] === 'ok') {
          alert('购买成功。\n花费：' + resp['cost'])
          this.$data.flush = false
          this.$data.flush = true
          this.loadBills()
        } else {
          alert('购买失败。错误消息：' + resp['status'])
        }
      } else {
        alert('购买失败。错误代码：' + response['status'])
      }
    })
  }
  removeFromCart (uuid: string) {
    HttpRequest.post('/del?uuid=' + uuid).then(response => {
      if (response['status'] === 200) {
        let resp = response['data']
        if (resp['status'] === 'ok') {
          alert('移除成功。')
          this.loadBills()
        } else {
          alert('移除失败。错误消息：' + resp['status'])
        }
      } else {
        alert('移除失败。错误代码：' + response['status'])
      }
    })
  }
  mounted () {
    if (!this.logger) this.logger = new Logger()
    this.$nextTick(() => this.logger.info('about is ready!'))
    this.loadBills()
  }

  private loadBills () {
    HttpRequest.get('/bill').then(response => {
      if (response['status'] === 200) {
        let resp = response['data']
        if (resp['status'] === 'ok') {
          this.$data.purchasedList = resp['data']
          if (this.$data.purchasedList.length !== 0) {
            this.purchasedTextField = '您有 ' + this.$data.purchasedList.length + ' 项已购项目。'
            for (let item of this.$data.purchasedList) {
              HttpRequest.get('/isbn', {
                params: {
                  isbn: item.isbn
                }
              }).then(response => {
                item['desc'] = response['data']['data'][0]['title'] + ' - ' + response['data']['data'][0]['author']
              })
            }
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
            for (let item of this.$data.cartList) {
              HttpRequest.get('/isbn', {
                params: {
                  isbn: item.isbn
                }
              }).then(response => {
                console.log('cart resp: ', response)
                item['desc'] = response['data']['data'][0]['title'] + ' - ' + response['data']['data'][0]['author']
              })
            }
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

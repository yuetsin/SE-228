import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'
// @ts-ignore
import global_ from '../../common/common'
import HttpRequest from '../../axios/api.request'

interface UserResponse {
  id: string
  name: string
}

@Component({
  template: require('./list.html'),
  data: function () {
    return {
      buyAnonymously: false,
      originPrice: '',
      couponPrice: '',
      type: '',
      isbn: '',
      bookAmount: undefined,
      cover: '',
      storage: 0,
      selectedDate: undefined,
      bookName: '十三个理由',
      author: 'Jay Asher',
      details: '中学生汉娜自杀后……'
    }
  },
  components: {
    'b-container': bContainer,
    'b-col': bCol,
    'b-row': bRow
  }
})
export class ListComponent extends Vue {

  addToCart () {
    if (this.$data.isbn === '') {
      return
    }
    HttpRequest.post('/buy', {
      params: {
        isbn: this.$data.isbn,
        count: this.$data.bookAmount,
        later: true
      }
    }).then(response => {
      console.log(response)
      if (response['status'] === 200) {
        if (response['data']['status'] === 'ok') {
          this.$router.push('/about')
        } else {
          alert('加入购物车失败。\n错误信息：' + response['data']['status'])
        }
      }
    })
  }
  mounted () {
    console.log('CALLED ENTER!')
    if (global_.highlightBook === undefined) {
      this.$router.push('/')
    }
    HttpRequest.get('/isbn', {
      params: {
        isbn: global_.highlightBook
      }
    }).then(response => {
      console.log(response)
      if (response['status'] === 200) {
        let rsp = response['data']
        if (rsp['status'] === 'ok') {
          rsp = rsp['data'][0]
          this.$data.cover = rsp['cover_url']
          this.$data.bookName = rsp['title']
          this.$data.originPrice = rsp['price']
          this.$data.isbn = rsp['isbn']
          this.$data.couponPrice = rsp['coupon_price']
          this.$data.author = rsp['author']
          this.$data.details = rsp['description']
          this.$data.storage = rsp['storage']
          this.$data.type = rsp['type']
          console.log(this.$data)
        } else {
          this.$router.push('/')
        }
      } else {
        this.$router.push('/')
      }
    })
  }
}

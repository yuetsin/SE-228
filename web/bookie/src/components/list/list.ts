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
      isbn: '',
      bookAmount: 'one',
      cover: '',
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

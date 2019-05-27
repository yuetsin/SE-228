import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'
// @ts-ignore
import global_ from '../../common/common'
// import HttpRequest from '../../axios/api.request'

interface UserResponse {
  id: string
  name: string
}

@Component({
  template: require('./list.html'),
  data: function () {
    return {
      flush: true,
      buyAnonymously: false,
      originPrice: '',
      couponPrice: '',
      type: '',
      isbn: '',
      bookAmount: 1,
      cover: 'default.png',
      storage: 0,
      selectedDate: undefined,
      bookName: '十三个理由',
      author: 'Jay Asher',
      details: '中学生汉娜自杀后……',
      comments: [],
      commContent: ''
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
    this.axios.post('/buy?isbn=' + this.$data.isbn + '&count=' + this.$data.bookAmount + '&later=true').then(response => {
      console.log(response)
      if (response['status'] === 200) {
        if (response['data']['status'] === 'ok') {
          this.$router.push('/')
        } else {
          alert('加入购物车失败。\n错误信息：' + response['data']['status'])
        }
      }
    })
  }
  purchaseBill () {
    this.axios.post('/buy?isbn=' + this.$data.isbn + '&count=' + this.$data.bookAmount + '&later=false')
      .then(response => {
        if (response['status'] === 200) {
          let resp = response['data']
          if (resp['status'] === 'ok') {
            alert('购买成功。总花费：' + resp['cost'] + '元')
            this.$router.push('/')
          } else {
            alert('购买失败。错误信息：' + resp['status'])
          }
        } else {
          alert('购买失败。错误代码：' + response['status'])
        }
      })
  }
  submitComment () {
    this.axios.post('/comment?content=' + this.$data.commContent + '&isbn=' + this.$data.isbn)
      .then(response => {
        if (response['status'] === 200) {
          let resp = response['data']
          if (resp['status'] === 'ok') {
            alert('评论成功！')
            this.$data.commContent = ''
            this.loadComments()
            this.$data.flush = false
            this.$data.flush = true
          } else {
            alert('提交评论错误。错误信息：' + resp['status'])
          }
        } else {
          alert('提交评论错误。错误代码：' + response['status'])
        }
      })
  }
  mounted () {
    this.loadComments()
  }

  private loadComments () {
    console.log('CALLED ENTER!')
    if (global_.highlightBook === undefined) {
      this.$router.push('/home')
    }
    this.axios.get('/isbn', {
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
          this.axios.get('/comlist', {
            params: {
              isbn: this.$data.isbn
            }
          }).then(response => {
            if (response['data']['status'] === 'ok') {
              this.$data.comments = response['data']['data']
            } else {
              console.log('failed to load comments. response: ', response)
            }
          })
          console.log(this.$data)
        } else {
          this.$router.push('/home')
        }
      } else {
        this.$router.push('/home')
      }
    })
  }
}

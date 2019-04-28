import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'
// @ts-ignore
import HttpRequest from '../../axios/api.request'
import './home.scss'
// @ts-ignore
import global_ from '../../common/common'

@Component({
  template: require('./home.html'),
  data: function () {
    return {
      bookLib: global_.selectedBooks,
      isAlive: true,
      ascending: false
    }
  },
  provide () {
    return {
      reload: this.reload
    }
  },
  components: {
    'b-container': bContainer,
    'b-col': bCol,
    'b-row': bRow
  }, methods: {
    reload () {
      this.$data.isAlive = false
      this.$nextTick(() => (this.$data.isAlive = true))
    },
    sortList () {
      if (this.$data.ascending) {
        this.$data.bookLib.sort((b1, b2) => (b1.title.length) - (b2.title.length))
      } else {
        this.$data.bookLib.sort((b1, b2) => (b2.title.length) - (b1.title.length))
      }
      this.$data.ascending = !this.$data.ascending
    },
    getimg (imgName: string) {
      HttpRequest.request({
        url: '/img/' + imgName,
        method: 'get'
      }).then(response => {
        console.log('done!')
      })
    },
    setGlobal (isbn: string) {
      global_.highlightBook = isbn
      console.log('Set isbn = ' + isbn)
      this.$router.push('/list')
    },
    addCart (isbn: string) {
      HttpRequest.post('/buy?isbn=' + isbn + '&count=1&later=true')
        .then(response => {
          if (response['status'] === 200) {
            let resp = response['data']
            if (resp['status'] === 'ok') {
              alert('添加成功。')
            } else {
              alert('添加购物车失败。错误信息：' + resp['status'])
            }
          } else {
            alert('添加购物车失败。错误代码：' + response['status'])
          }
        })
    }
  }
})

export class HomeComponent extends Vue {
  mode: string = process.env.ENV
}

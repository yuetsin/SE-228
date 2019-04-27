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
        global_.selectedBooks.sort((b1, b2) => (b1.title.length) - (b2.title.length))
      } else {
        global_.selectedBooks.sort((b1, b2) => (b2.title.length) - (b1.title.length))
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
    }
  }
})

export class HomeComponent extends Vue {
  mode: string = process.env.ENV
}

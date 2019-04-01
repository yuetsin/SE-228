import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'

import './home.scss'
// @ts-ignore
import global_ from '../../common/common'
import {Book} from '../../struct/book'

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
        global_.selectedBooks.sort((b1, b2) => (b1.bookName.length) - (b2.bookName.length))
      } else {
        global_.selectedBooks.sort((b1, b2) => (b2.bookName.length) - (b1.bookName.length))
      }
      this.$data.ascending = !this.$data.ascending
    }
  }
})

export class HomeComponent extends Vue {
  mode: string = process.env.ENV
}

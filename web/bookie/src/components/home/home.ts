import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'

import './home.scss'
// @ts-ignore
import global_ from '../../common/common'

@Component({
  template: require('./home.html'),
  data: function () {
    return {
      bookLib: global_.selectedBooks,
      isAlive: true
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
    }
  }
})

export class HomeComponent extends Vue {
  mode: string = process.env.ENV
}

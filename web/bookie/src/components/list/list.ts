import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'

interface UserResponse {
  id: string
  name: string
}

@Component({
  template: require('./list.html'),
  components: {
    'b-container': bContainer,
    'b-col': bCol,
    'b-row': bRow
  }
})
export class ListComponent extends Vue {
  buyAnonymously = false
  selectedDate = null
  bookAmount = 'one'
  cover = '../../assets/img/thirteenReasonsWhy.png'
  bookName = '十三个理由'
  author = 'Jay Asher'
  details = '中学生汉娜自杀后，留下十三盒磁带。分别寄给十三个同学，在录音中，她一一追诉往事，发出质询……随着一盒又一盒磁带的揭示，在悬念丛生中，反思了中学阶段时人文教育的缺失，发人深思。'
}

import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'

import './home.scss'

@Component({
  template: require('./home.html'),
  components: {
    'b-container': bContainer,
    'b-col': bCol,
    'b-row': bRow
  }
})
export class HomeComponent extends Vue {

  mode: string = process.env.ENV

  books = [{
    cover: '../../assets/img/primeNumber.png',
    bookName: '质数的孤独',
    author: 'Paolo Giordano',
    details: '马蒂亚是一个年轻的数学天才，他相信自己是质数中的一个，而中学同学爱丽丝正是他的孪生质数。他们都有痛苦的过往，同样孤独，同样无法拉近和其他人之间的距离。从少年到成年，他们的生命不断交叉，努力消除存在于彼此间障碍，相互影响又彼此分离，就像孪生质数，彼此相近却永远无法靠近。'
  }, {
    cover: '../../assets/img/theKiteRunner.png',
    bookName: '追风筝的人',
    author: 'Khaled Hosseini',
    details: '「许多年过去了。人们说陈年旧事可以被埋葬，然而我终于明白这是错的，因为往事会自行爬上来。回首前尘，我意识到在过去二十六年里，自己始终在窥视着那荒芜的小径。」'
  }, {
    cover: '../../assets/img/thirteenReasonsWhy.png',
    bookName: '十三个理由',
    author: 'Jay Asher',
    details: '中学生汉娜自杀后，留下十三盒磁带。分别寄给十三个同学，在录音中，她一一追诉往事，发出质询……随着一盒又一盒磁带的揭示，在悬念丛生中，反思了中学阶段时人文教育的缺失，发人深思。'
  }, {
    cover: '../../assets/img/fangSiQi.png',
    bookName: '房思琪的初戀樂園',
    author: '林奕含',
    details: '人喜欢的就是文饰过非。明明在做一件错事，却还是喜欢用漂亮的话来美化、掩盖丑恶。李国华是这样，高育良也是如此。当真相被揭开，人性的劣根性彻底暴露，所有被谎言堆砌的美好轰然倒塌，那些在谎言里活着的人才会彻悟。'
  }]
}

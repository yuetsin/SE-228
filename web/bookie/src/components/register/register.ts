import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'
// @ts-ignore
import global_ from '../../common/common'

@Component({
  template: require('./register.html'),
  components: {
    'b-container': bContainer,
    'b-col': bCol,
    'b-row': bRow
  }
})
export class RegisterComponent extends Vue {
  userName = ''
  passWord = ''
  agreedLicense = false
  registerSucceed = false

  canRegister () {
    // @ts-ignore
    return !(this.userName.length >= 8 && (this.passWord.length >= 6) && this.agreedLicense)
  }
  setUserName () {
    global_.userName = this.userName
    this.$router.replace({
      path: '/_empty'
    })

    this.$router.replace({
      path: '/'
    })
    this.$router.go(0)
  }
}

import { Component, Vue } from 'vue-property-decorator'
import bContainer from 'bootstrap-vue/es/components/layout/container'
import bCol from 'bootstrap-vue/es/components/layout/col'
import bRow from 'bootstrap-vue/es/components/layout/row'
// @ts-ignore
import global_ from '../../common/common'
// import HttpRequest from '../../axios/api.request'
@Component({
  template: require('./register.html'),
  data: function () {
    return {
      userName: '',
      passWord: ''
    }
  },
  components: {
    'b-container': bContainer,
    'b-col': bCol,
    'b-row': bRow
  }
})
export class RegisterComponent extends Vue {

  agreedLicense = false
  registerSucceed = false

  canRegister () {
    // @ts-ignore
    return !(this.userName.length >= 8 && (this.passWord.length >= 6) && this.agreedLicense)
  }
  postRegister () {
    console.log(this.$data.userName, this.$data.passWord)
    this.axios.post('/reg?username=' + this.$data.userName + '&password=' + this.$data.passWord).then(response => {
      let rsp = response['data']
      if (rsp['status'] !== 'ok') {
        alert('注册失败，请再试一次。\n错误信息：' + rsp['status'])
        return
      }
      this.registerSucceed = true
      this.$router.push('/logout')
    })
  }
}

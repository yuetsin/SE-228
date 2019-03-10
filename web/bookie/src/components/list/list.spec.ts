import Component from 'vue-class-component'
import { expect } from 'chai'
import { ComponentTest } from '../../util/component-test'
import { ListComponent } from './list'

describe('List component', () => {
  let directiveTest: ComponentTest

  beforeEach(() => {
    directiveTest = new ComponentTest('<div><list></list></div>', { 'list': ListComponent })
  })

  it('should render correct contents', async () => {
    directiveTest.createComponent()

    await directiveTest.execute((vm) => { // ensure Vue has bootstrapped/run change detection
      debugger
      console.log(vm.$el.querySelectorAll('.content ul li'))
      expect(vm.$el.querySelectorAll('.content ul li').length).to.equal(3)
    })
  })
})

declare module '*.vue' {
  import Vue from 'vue'
  export default Vue
}

declare module 'common/common' {
  interface Vue {
    book_library,
    version
  }
}

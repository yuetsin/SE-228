<script type="text/javascript">
  const bookLibrary = require('../assets/books/books.json')['oh_my_books']
  const version  = "1.0.2.20190401"
  let selectedBooks = []
  let userName = undefined
  let counter = 0
  const MAX_NUMBER = 4
  for (let i of bookLibrary) {
    counter++;
    selectedBooks.push(i)
    if (counter > MAX_NUMBER) {
      break
    }
  }
  export default {
    bookLibrary,
    version,
    selectedBooks,
    MAX_NUMBER,
    userName
  }
</script>

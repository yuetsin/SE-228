<template>
  <form id="login" novalidate @submit.stop.prevent="open">
    <div class="starter-template">
      <p v-if="${param.logout}" class="bg-warning">已成功注销</p><!-- 1 -->
      <p v-if="${param.error}" class="bg-danger">有错误，请重试</p> <!-- 2 -->
      <h2>使用用户名密码登录</h2>
      <form name="form" href="@{/login}" action="/login" method="POST"> <!-- 3 -->
        <div class="form-group">
          <label for="username">账号</label>
          <input type="text" class="form-control" name="username" value="" placeholder="账号" />
        </div>
        <div class="form-group">
          <label for="password">密码</label>
          <input type="password" class="form-control" name="password" placeholder="密码" />
        </div>
        <div class="form-group">
          <input type="submit" id="login" value="登录" class="btn btn-primary" />
        </div>
      </form>
    </div>
  </form>
</template>

<script>
  export default {
    name: 'login'
  }
</script>

<style scoped>

</style>

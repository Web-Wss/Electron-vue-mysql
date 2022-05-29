<template>
  <!-- <div class="box">
    用户名: <input type="text" v-model="username" /> 密码:
    <input type="password" v-model="password" />
    <button @click="login">登录</button>
  </div> -->

  <div>
    <div class="login-wrap">
      <el-form
        class="login-form"
        label-position="top"
        label-width="80px"
        :model="formdata"
      >
        <h2>后台系统登录</h2>
        <el-form-item label="用户名">
          <el-input v-model="formdata.username"></el-input>
        </el-form-item>
        <el-form-item label="密  码">
          <el-input type="password" v-model="formdata.password"></el-input>
        </el-form-item>
        <el-button @click="login()" class="login-btn" type="primary"
          >登录</el-button
        >
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formdata: {
        username: "",
        password: "",
      },
    };
  },

  methods: {
    login() {
      this.$http
        .get(
          `/api/LoginServlet?username=${this.formdata.username}&password=${this.formdata.password}`
        )
        .then((res) => {
          console.log(res);
          if (res.data != null) {
            this.$router.push("/zl");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.login-wrap {
  height: 100vh;
  background-color: #f6f6f6;
  /* 弹性盒模型 */
  display: flex;
  /* 水平垂直居中 */
  justify-content: center;
  align-items: center;
  /* opacity: 0.5; */
}
.login-wrap .login-form {
  width: 400px;
  background-color: #fff;
  border-radius: 5px;
  padding: 30px;
  /* opacity: 0.5; */
}
.login-wrap .login-btn {
  /* margin-top: 10px; */
  width: 200px;
  margin-left: calc(50% - 100px);
}
</style>

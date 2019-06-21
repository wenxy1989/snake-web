<template>
  <div>
    <div class="header">
      <span class="header-title"><i>动态血糖监测</i></span>
      <span class="header-management">管理平台</span>
    </div>
    <div class="main">
      <Row class="main-content">
        <i-col :span="3">
        </i-col>
        <i-col :span="15">
          <img class="content-img" src="../assets/images/login/chart.png" />
        </i-col>
        <i-col :span="6">
          <Card class="login-card" title="用户登录">
            <Form :label-width="76">
              <FormItem label="登录名称">
                <Input v-model="user.loginName" prefix="ios-person-outline" placeholder="登录名/手机号/邮箱" />
              </FormItem>
              <FormItem label="登录密码">
                <Input v-model="user.loginPwd" type="password" prefix="ios-lock-outline" placeholder="请输入密码" />
              </FormItem>
              <FormItem>
                <Button type="primary" @click="doLogin">登录</Button>
              </FormItem>
            </Form>
          </Card>
        </i-col>
      </Row>
    </div>
    <div class="footer">
      <p>浙ICP备05057467号（浙）非经营性 2012-2014 Tel:4006 725 725 Email:info@sanmeditech.com</p>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      user: {
        loginName: 'admin',
        loginPwd: 'mko09ijn'
      }
    }
  },
  methods: {
    doLogin () {
      const vm = this
      this.$axios.post('/login', this.user)
        .then(data => {
          localStorage.setItem('AuthorizationToken', data)
          vm.$router.push({
            path: './main'
          })
        })
    }
  }
}
</script>
<style scoped>
.header {
  height: 200px;
  padding-top: 138px;
  text-align: center;
}
.header-title {
  padding: 0px 45px 3px 45px;
  background: url(../assets/images/login/title-back.png);
  background-size: 342px 60px;
  background-repeat: no-repeat;
  color: aliceblue;
  font-size: 40px;
  font-weight: bold;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}
.header-management {
  color: #053363;
  font-size: 40px;
  font-weight: bold;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}
.main {
  height: 556px;
  background: url(../assets/images/login/body.jpg);
  background-repeat: no-repeat;
}
.main-content {
  top: 54px;
  height: 256px;
}
.content-img {
  margin-top: 18px;
  margin-left: 24px;
}
.footer {
  text-align: center;
}
.login-card {
  width: 360px;
  opacity: 0.86;
}
</style>

<template>
  <div class="wrapper" :style="wrapper">
    <div style="background-color: #008EAD; height: 50%">
      <h1 class="title">欢迎来到遗传算法排课系统</h1>
      <div class="inner">
        <h3 style="height: 60px"><router-link to="admin/login" style="text-decoration:none;">管理员讲师入口</router-link></h3>
        <h3 style="height: 60px"><router-link to="student/login" style="text-decoration:none;">学生入口</router-link></h3>
      </div>
    </div>
    <!--<h3 class="footer"><router-link to="/" style="text-decoration:none;">联系管理员</router-link></h3>-->
    <div>
      <h3 style="margin-top: 270px">Copy Right © 2020-2021 xiaoxu111 版权所有
        <el-tooltip placement="top">
          <div slot="content">QQ ： 1462638689<br/>微信 ：15225200721</div>
          <el-button type="primary">联系管理员</el-button>
        </el-tooltip>
      </h3>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data() {
    return {
      wrapper: {
         // backgroundImage: "url(" + require("../images/image2.jpg") + ")",
            backgroundRepeat: "no-repeat",

      },
    }
  }
}
</script>

<style lang="less" scoped>
  .wrapper {
    /*background-image: "@images/psb.jpg";*/
    height: 100%;
    width: 100%;
    position: absolute;
  }

  .title {
    text-align: center;
    margin-left: 30px;
    margin-top: 10px;
  }
</style>

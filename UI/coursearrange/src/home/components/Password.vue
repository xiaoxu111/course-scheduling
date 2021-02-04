<template>
  <div class="password-center">
    <el-form label-width="80px" ref="regFormpsw" :model="form" :rules="passwordRules">
      <el-form-item label="原始密码" prop="oldPass">
        <el-input required="true" placeholder="请输入旧密码" v-model="form.oldPass" type="password"  show-password clearable></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPass">
        <el-input placeholder="请输入新密码" v-model="form.newPass" type="password" show-password clearable></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="rePass">
        <el-input placeholder="确认新密码" v-model="form.rePass" type="password" show-password clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="save()">修改</el-button>
        <el-button type="primary" @click="resetForm('regFormpsw')"  size="small">重置</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
export default {
  name: "password",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.form.newPass !== '') {
          this.$refs.regFormpsw.validateField('rePass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form.newPass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      form: {},
      student:{},
      passwordRules:{
        oldPass: [
          { required: true, message: '请输入旧密码', trigger: 'blur' },
          {  trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ],
        newPass: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ],
        rePass: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ]
      }
    };
  },
  mounted() {
    if (window.localStorage.getItem("student") != null) {
      this.student = JSON.parse(window.localStorage.getItem("student"));
    } else {
      this.$message({
        message: "获取本地用户信息失败，请重新登录",
        type: "error"
      });
    }
  },
  methods: {
    save() {
      let data = this.form;
      data.id = this.student.id;
      this.$axios
        .post(
          "http://localhost:8080/student/password",
          data
        )
        .then(r => {
          if (r.data.code == 0) {
            this.$message({
              message: '密码更新成功，请重新登录！',
              type: "success"
            });
            setTimeout(() => {
              window.localStorage.removeItem('student')
              this.$router.push('/student/login')
            }, 1000);
          }else{
            this.$message({
              message: r.data.message,
              type: "error"
            });
          }
          this.form = {}
        });
    },
    // 重置按钮
    resetForm(regFormpsw) {
      this.$refs[regFormpsw].resetFields();
    }
  }
};
</script>

<style lang="less">
.password-center {
  margin-left: 330px;
  margin-top: 120px;
  width: 500px;
  text-align: center;
  padding: 30px;
}
</style>

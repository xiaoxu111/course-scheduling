<template>
  <div class="register-wrapper">
    <!-- 注册表单 -->
    <div class="register-box">
      <!-- 图标 -->
      <div class="register-avatar">
        <img src="@/assets/regist.png"/>
      </div>

      <el-form class="register-form" ref="regFormRef" :model="studentRegNewForm" :rules="studentRegRules">
        <h3>学生注册</h3>
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input placeholder="请输入用户名" v-model="studentRegNewForm.username" autocomplete="off" clearable></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" v-model.trim="studentRegNewForm.password" show-password></el-input>
        </el-form-item>
        <el-form-item prop="password2">
          <el-input placeholder="确认密码" v-model.trim="studentRegNewForm.password2" show-password
                    autocomplete="off"></el-input>
        </el-form-item>
        <!-- 真实姓名 -->
        <el-form-item prop="realname">
          <el-input class="ele" placeholder="请输入真实姓名" v-model="studentRegNewForm.realname" clearable></el-input>
        </el-form-item>
        <!-- 年级 -->
        <template>
          <!--<el-form-item prop="grade">-->
          <el-select class="select-grade" @change="handleSelectChange" v-model="studentRegNewForm.grade" clearable
                     placeholder="请选择年级再点击创建学号">
            <el-option
              v-for="item in studentRegNewForm.options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
          <!--</el-form-item>-->
        </template>
        <!-- 学号 -->
        <div class="ele">
          <el-input
            class="studentNo-input"
            placeholder="点击右侧按钮生成一个学号"
            v-model="studentRegNewForm.studentNo"
            slot="prepend"
            :disabled="true">
            <!-- 生成学号按钮 -->
            <el-button @click="createStuNo" slot="append" type="primary">创建学号</el-button>
          </el-input>
        </div>
        <!-- 联系电话 -->
        <el-form-item prop="telephone">
          <el-input class="ele" placeholder="联系电话" v-model="studentRegNewForm.telephone" clearable></el-input>
        </el-form-item>
        <!-- 邮件 -->
        <el-form-item prop="email">
          <el-input class="ele" placeholder="电子邮件" v-model="studentRegNewForm.email" clearable></el-input>
        </el-form-item>
        <!-- 地址 -->
        <el-form-item prop="address">
          <el-input class="ele" placeholder="家庭住址" v-model="studentRegNewForm.address" clearable></el-input>
        </el-form-item>
        <!-- 按钮 -->
        <el-form-item class="button">
          <el-button type="primary" @click="register">注册</el-button>
          <el-button type="info" @click="hasNo">已有帐号</el-button>
          <el-button @click="resetForm('regFormRef')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data() {
    // 校验手机号
    var checkphone = (rule, value, callback) => {
      // let phoneReg = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^09\d{8}$)/;
      if (value == "") {
        callback(new Error("请输入手机号"));
      } else if (!this.isCellPhone(value)) {//引入methods中封装的检查手机格式的方法
        callback(new Error("请输入正确的手机号(11位数字)!"));
      } else {
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.studentRegNewForm.password !== '') {
          this.$refs.regFormRef.validateField('password2');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.studentRegNewForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };

    return {
      studentRegNewForm: {
        studentNo: '',
        username: '',
        password: '',
        password2: '',
        realname: '',
        telephone: '',
        address: '',
        email: '',
        options: [
          {
            value: '01',
            label: '高一'
          }, {
            value: '02',
            label: '高二'
          }, {
            value: '03',
            label: '高三'
          }],
        grade: '',
        gradeName: ''
      },

      studentRegRules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {validator: validatePass, trigger: 'blur'},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ],
        password2: [
          {required: true, message: '请确认密码', trigger: 'blur'},
          {validator: validatePass2, trigger: 'blur'},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ],
        telephone: [{required: true, validator: checkphone, trigger: "blur"}],
        studentNo: [
          {required: true, message: '请先给自己申请个学号', trigger: 'blur'},
        ],
        email: [
          {required: true, message: '请输入邮件', trigger: 'blur'},
          {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']},
        ]
      }
    }
  },

  methods: {
    // 检查手机号
    isCellPhone(val) {
      if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
        return false;
      } else {
        return true;
      }
    },
    // 得到对应选中的年级
    handleSelectChange(v) {
      this.studentRegNewForm.options.map((k) => {
        if (k.value === v) {
          this.studentRegNewForm.gradeName = k.label
          return
        }
      })
    },

    hasNo() {
      // 已经有账号，跳转到登录界面
      window.location.href = "http://localhost:8081/#/student/login"
    },

    // 创建学号按钮响应，返回学号并填充到学号栏
    createStuNo() {
      // 把年级对应的编号this.studentRegNewForm.grade带过去
      if (this.studentRegNewForm.grade == "" || this.studentRegNewForm.grade == undefined || this.studentRegNewForm.grade == null) {
        this.$message.error('请先选择所在的年级！！！');
      }
      this.$axios.post('http://localhost:8080/student/createstudentno/' + this.studentRegNewForm.grade, {})
        .then(res => {
          // 弹出申请学号成功的提示
          if (res.data.code == 0) {
            this.studentRegNewForm.studentNo = res.data.message
            this.$message({
              message: '申请学号成功，请牢记您的学号(登录使用！)：' + this.studentRegNewForm.studentNo,
              type: 'success'
            });
          }
          if (res.data.code == 1) {
            this.studentRegNewForm.studentNo = res.data.message
            this.$message.error('申请学号失败，请联系管理员');
          }
        })
        .catch(error => {
          this.$message.error(res.data.message);
          // alert(res.data.message)
        })
    },
    // 重置按钮
    resetForm(regFormRef) {
      this.$refs[regFormRef].resetFields();
    },
    // 注册按钮方法
    register() {
      // 进行表单预验证
      this.$refs.regFormRef.validate(valid => {
        if (!valid) return;
        // 进行注册， 携带填写的注册信息给后台
        this.$axios.post('http://localhost:8080/student/regist', {
          username: this.studentRegNewForm.username,
          password: this.studentRegNewForm.password,
          realname: this.studentRegNewForm.realname,
          // 年级:高一，高二这种形式
          grade: this.studentRegNewForm.gradeName,
          studentNo: this.studentRegNewForm.studentNo,
          telephone: this.studentRegNewForm.telephone,
          address: this.studentRegNewForm.address,
          email: this.studentRegNewForm.email
        })
          .then(res => {
            // 注册成功
            if (res.data.code == 0) {
              this.$message({
                message: '注册成功，请用你的学号！：' + res.data.data.studentNo + '登录系统',
                type: 'success'
              });
              // alert('注册成功，请用你的学号' + res.data.data.studentNo+'登录系统')
              this.$router.push('/student/login')
            } else {
              this.$message.error(res.data.message);
              // alert(res.data.message)
            }
          })
          .catch(error => {
            this.$message.error('注册失败，请重试!!');
            // alert('注册失败，请重试')
          })
      })
    },


  }
}
</script>

<style lang="less" scoped>

.ele {
  margin-top: 1px;
}

.studentNo-input {
  margin-top: 1px;
}

.button {
  display: flex;
  margin-top: 15px;
  margin-right: 30px;
  justify-content: center;
}

.register-form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.register-wrapper {
  background: #2b4b6b;
  height: 100%;
}

.select-grade {
  margin-top: 2px;
  float: left;
  width: 100%;
}

.register-box {
  width: 550px;
  height: 635px;
  background-color: #fff;
  border-radius: 2px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.register-avatar {
  height: 80px;
  width: 80px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;

  img {
    height: 90%;
    width: 90%;
    border-radius: 50%;
    background-color: #eee;
  }
}

</style>

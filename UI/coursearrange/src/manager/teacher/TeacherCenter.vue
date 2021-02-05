<template>
  <div class="T-center">
    <el-form label-width="80px" ref="regFormRef" :model="teacherForm" :rules="teacherCenterRules">
      <el-form-item label="账号">
        <el-input v-model="teacherForm.teacherNo" disabled></el-input>
      </el-form-item>
      <el-form-item label="真实姓名">
        <el-input v-model="teacherForm.realname" disabled></el-input>
      </el-form-item>
      <el-form-item label="职称">
        <el-input v-model="teacherForm.jobTitle" disabled></el-input>
      </el-form-item>
      <el-form-item label="所属年级" >
        <el-input v-model="teacherForm.gradeNo" disabled></el-input>
      </el-form-item>
      <el-form-item label="教授科目" prop="teachSubject">
        <el-input v-model="teacherForm.teachSubject" clearable></el-input>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="teacherForm.age" clearable></el-input>
      </el-form-item>
      <el-form-item label="居住地址" prop="address">
        <el-input v-model="teacherForm.address" clearable></el-input>
      </el-form-item>
      <el-form-item label="简介" prop="description">
        <el-input v-model="teacherForm.description" clearable></el-input>
      </el-form-item>
      <el-form-item label="手机" prop="telephone">
        <el-input v-model="teacherForm.telephone" clearable></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="teacherForm.email"  clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="saveInfo()">保存</el-button>
        <el-button type="primary" @click="resetForm('regFormRef')"  size="small">重置</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
  export default {
    name: "TeacherCenter",
    data() {
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
      return {
        teacherForm: {},
        visibleForm: false,
        editFormData: {},
        options: [],
        // 规则校验
        teacherCenterRules: {
          email: [
            {required: true, message: '请输入邮件', trigger: 'blur'},
            {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']},
          ],
          telephone: [{ required: true, validator: checkphone, trigger: "blur" }],
        }
      };
    },
    mounted() {
      let teacher = window.localStorage.getItem("teacher");
      if (teacher != null) {
        this.teacherForm = JSON.parse(teacher);
      } else {
        this.$message({
          message: "获取本地用户信息失败，请重新登录",
          type: "error"
        });
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
      saveInfo() {
        this.$axios
          .post("http://localhost:8080/admin/modify/", this.teacherForm)
          .then(r => {
          // 弹出成功提示
          if (r.data.code == 0) {
          this.$message({
            message: r.data.message,
            type: "success"
          });
          window.localStorage.setItem('teacher', JSON.stringify(this.teacherForm))
        }
        // 弹出失败提示
        if (r.data.code == 1) {
          this.$message.error(r.data.message);
        }
      });
      },
      // 重置按钮
      resetForm(regFormRef) {
        this.$refs[regFormRef].resetFields();
      },
    }
  };
</script>

<style lang="less">
  .T-center {
    margin-left: 330px;
    width: 500px;
    text-align: center;
    padding: 30px;
  }
</style>

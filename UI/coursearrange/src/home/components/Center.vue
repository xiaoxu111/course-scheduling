<template>
  <div class="s-center">
    <el-form label-width="80px" ref="regFormRef" :model="form" :rules="studentCenterRules">
      <el-form-item label="学号">
        <el-input v-model="form.studentNo" disabled></el-input>
      </el-form-item>
      <el-form-item label="真实姓名">
        <el-input v-model="form.realname" disabled></el-input>
      </el-form-item>
      <el-form-item label="年级">
        <el-input v-model="form.grade" disabled></el-input>
      </el-form-item>
      <el-form-item label="我的班级">
        <el-input v-model="form.classNo">
          <el-button slot="append" type="primary" @click="handleJoinClass()">加入班级</el-button>
        </el-input>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="form.age" clearable></el-input>
      </el-form-item>
      <el-form-item label="居住地址" prop="address">
        <el-input v-model="form.address" clearable></el-input>
      </el-form-item>
      <el-form-item label="简介" prop="description">
        <el-input v-model="form.description" clearable></el-input>
      </el-form-item>
      <el-form-item label="手机" prop="telephone">
        <el-input v-model="form.telephone" clearable></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="form.email"  clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="saveInfo()">保存</el-button>
        <el-button type="primary" @click="resetForm('regFormRef')"  size="small">重置</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="加入班级" :visible.sync="visibleForm" width="500px">
      <el-form :model="editFormData" label-position="right" label-width="80px">
        <el-form-item label="班级" prop="classNo">
          <el-select v-model="editFormData.classNo" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="text-align:left;">
          <el-button type="primary" @click="save()" size="small">提 交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "center",
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
      form: {},
      visibleForm: false,
      editFormData: {},
      options: [],
      // 规则校验
      studentCenterRules: {
        email: [
          {required: true, message: '请输入邮件', trigger: 'blur'},
          {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']},
        ],
        telephone: [{ required: true, validator: checkphone, trigger: "blur" }],
      }
    };
  },
  mounted() {
    let student = window.localStorage.getItem("student");
    if (student != null) {
      this.form = JSON.parse(student);
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
    save() {
      this.$axios
        .post(
          "http://localhost:8080/student/join/" +
            this.form.id +
            "/" +
            this.editFormData.classNo
        )
        .then(r => {
          // 弹出成功提示
          if (r.data.code == 0) {
            this.$message({
              message: r.data.message,
              type: "success"
            });
            this.form.classNo = this.editFormData.classNo
            window.localStorage.setItem('student', JSON.stringify(this.form))
          }
          // 弹出失败提示
         if (r.data.code == 1) {
           this.$message.error(r.data.message);
      }
        });
        this.visibleForm = false;
    },
    saveInfo() {
      this.$axios
        .post("http://localhost:8080/student/modify/", this.form)
        .then(r => {
          // 弹出成功提示
          if (r.data.code == 0) {
            this.$message({
              message: r.data.message,
              type: "success"
            });
            window.localStorage.setItem('student', JSON.stringify(this.form))
          }
          // 弹出失败提示
         if (r.data.code == 1) {
            this.$message.error(r.data.message);
            // window.localStorage.setItem('student', JSON.stringify(this.form))
          }
        });
    },
    handleJoinClass() {
      let grade = this.form.studentNo.substring(4, 6);
      this.options = [];
      this.visibleForm = true;
      this.$axios.get("http://localhost:8080/by-grade-for-class/" + grade).then(r => {
        let data = r.data.data;
        data.map(v => {
          this.options.push({
            label: v.classNo,
            value: v.classNo
          });
        });
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
.s-center {
  margin-left: 330px;
  width: 500px;
  text-align: center;
  padding: 30px;
}
</style>

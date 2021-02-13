<template>
  <div>
    <!-- 功能 -->
    <div class="header-menu">
      <el-select
        v-model="value1"
        placeholder="年级"
        @change="queryClassByGrade"
        @clear="clearListener"
        clearable
      >
        <el-option v-for="item in grade" :key="item.value" :label="item.label" :value="item.value"></el-option>
      </el-select>
      <el-button slot="trigger" type="primary" @click="addClass()">
        新增班级
        <!-- <i class="el-icon-folder-add el-icon--right"></i> -->
      </el-button>
    </div>
    <div>
      <el-table :data="classInfoData" size="mini" :stripe="true" :highlight-current-row="true">
        <el-table-column label="序号" type="selection"></el-table-column>
        <el-table-column prop="gradeName" label="年级"></el-table-column>
        <el-table-column prop="classNo" label="班级编号"></el-table-column>
        <el-table-column prop="className" label="班级名称"></el-table-column>
        <el-table-column prop="realname" label="班主任"></el-table-column>
        <el-table-column prop="num" label="学生人数"></el-table-column>

        <el-table-column prop="operation" label="操作" width="150px">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="deleteById(scope.$index, scope.row)">删除</el-button>
            <el-button type="primary" size="mini" @click="editById(scope.$index, scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 添加班级 -->
      <el-dialog title="添加班级" :visible.sync="visible">
        <el-form :model="addClassData" label-position="left" label-width="80px">
          <el-select v-model="addClassData.gradeNo" placeholder="年级" clearable>
            <el-option
              v-for="item in grade"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
          <!-- 选择班主任 -->
          <el-select v-model="addClassData.id" placeholder="班主任">
            <el-option
              v-for="item in teacher"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
          <el-form-item label="班级编号" prop="classNo">
            <el-input v-model="addClassData.classNo" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="班级名称" prop="className">
            <el-input v-model="addClassData.className" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="学生人数" prop="num">
            <el-input v-model="addClassData.num" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="visible = false">取 消</el-button>
          <el-button type="primary" @click="commit()">提 交</el-button>
        </div>
      </el-dialog>


      <!-- 弹出表单编辑学生 -->
      <el-dialog title="编辑班级" :visible.sync="visibleEdit">
        <el-form
          :model="editFormData"
          label-position="left"
          label-width="80px"
          :rules="editFormRules"
        >
          <el-form-item label="年级">
            <el-input v-model="editFormData.gradeName" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="班级编号" prop="classNo">
            <el-input v-model="editFormData.classNo" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="班级名称" prop="className">
            <el-input v-model="editFormData.className" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="班主任" prop="realname">
            <el-input v-model="editFormData.realname" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="学生人数" prop="num">
            <el-input v-model="editFormData.num" autocomplete="off" ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="visibleEdit = false">取 消</el-button>
          <el-button type="primary" @click="commitupdate()">提 交</el-button>
        </div>
      </el-dialog>

      <!-- 上一页，当前页，下一页 -->
      <div class="footer-button">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="page"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClassManager",
  data() {
    return {
      classInfoData: [],
      editFormData: [],
      addClassData: {
        gradeNo: '',
        num: 0,
        id: ''
      },
      visibleAdd: false,
      visibleEdit: false,
      visible: false,
      page: 1,
      pageSize: 10,
      total: 0,
      value1: '',
      value2: '',
      grade: [
        {
          value: "01",
          label: "高一"
        },
        {
          value: "02",
          label: "高二"
        },
        {
          value: "03",
          label: "高三"
        }
      ],
      teacher: [
        {
          value: "",
          label: ""
        }
      ],
      editFormRules: {
        gradeName: [{ required: true, message: "请输入年级", trigger: "blur" }],
        classNo: [{ required: true, message: "请输入班级编号", trigger: "blur" }],
        className: [{ required: true, message: "请输入班级名称", trigger: "blur" }],
        realname: [{ required: true, message: "请输入班主任", trigger: "blur" }],
        num: [{ required: true, message: "请输入班级容量", trigger: "blur" }],
      }
    };
  },
  mounted() {
    this.allClassInfo();
  },
  methods: {
    // 提交添加班级
    commit() {
      this.$axios.post("http://localhost:8080/addclassinfo", this.addClassData)
      .then(res => {
        if (res.data.code == 0) {
          this.allClassInfo()
          this.$message({message: "添加班级成功", type: "success"})
          this.visible = false
          this.addClassData = []
        } else {
          alert(res.data.message)
        }
      })
      .catch(error => {
        this.$message.error("添加班级失败")
      })
    },

    /***
     * 编辑班级信息提交
     */
    commitupdate() {
      this.modifyClass(this.editFormData)
    },

    /**
     * 更新学生
     */
    modifyClass(modifyData) {
      this.$axios
        .post("http://localhost:8080/modifyClass/" + this.editFormData.id, modifyData)
        .then(res => {
        if(res.data.code ==0){
        this.$message({ message: res.data.message , type: "success" })
        this.allClassInfo()
        this.visibleEdit = false
      }else{
        this.$message.error(res.data.message)
        this.allClassInfo()
        this.visibleEdit = false
      }
    })
    .catch(error => {
        this.$message.error("更新失败")
      this.allClassInfo()
      // this.visibleForm = false
    });
    },

    addClass() {
      this.allTeacher()
      this.visible = true
    },

    // 查询所有讲师
    allTeacher() {
      this.$axios.get("http://localhost:8080/teacher/all")
      .then(res => {
        console.log(res)
        if (res.data.code == 0) {
          let ret = res.data.data
          this.teacher.splice(0, this.teacher.length)
          this.value2 = ''
          ret.map(v => {
            this.teacher.push({
              value: v.id,
              label: v.realname
            })
          })
        } else {
          this.$message.error(res.data.message)
        }
      })
      .catch(error => {})
    },

    handleSizeChange() {},

    handleCurrentChange(v) {
      this.page = v;
      this.allClassInfo();
    },

    // 根据年级查询班级
    queryClassByGrade() {
      this.$axios
        .get(
          "http://localhost:8080/queryclassinfo/" +
            this.page +
            "?gradeNo=" +
            this.value1
        )
        .then(res => {
          if (res.data.code == 0) {
            let ret = res.data.data;
            this.classInfoData = ret.records;
            this.total = ret.total;
          }
        })
        .catch(error => {});
    },

    // 分页查询所有班级
    allClassInfo() {
      this.$axios
        .get("http://localhost:8080/queryclassinfo/" + this.page)
        .then(res => {
          if (res.data.code == 0) {
            let ret = res.data.data;
            this.classInfoData = ret.records;
            this.total = ret.total;
          }
        })
        .catch(error => {});
    },

    // 清除年级后重新查询所有班级
    clearListener() {
      this.value1 = "";
      this.allClassInfo();
    },

    // 根据班级id删除班级信息
    deleteById(index, row) {
      this.deleteClassById(row.id)
    },
    // 根据班级id删除班级信息
    deleteClassById(id) {
      this.$axios
        .delete("http://localhost:8080/deleteclass/" + id)
        .then(res => {
          //删除成功
          if(res.data.code == 0){
        this.$message({ message: res.data.message, type: "success" })
        this.allClassInfo()
      }
      // 删除失败
      if (res.data.code == 1) {
        this.$message.error(res.data.message)
      }
    })
    .catch(error => {
        this.$message.error(res.data.message)
    });
    },

    editById(index, row) {
      let modifyId = row.id
      this.editFormData = row
      this.visibleEdit = true
    }
  }
};
</script>

<style lang="less" scoped>
.header-menu {
  margin-bottom: 5px;
  padding: 0;
  text-align: left;
  margin-bottom: 5px;
}

.footer-button {
  margin-top: 10px;
}
</style>

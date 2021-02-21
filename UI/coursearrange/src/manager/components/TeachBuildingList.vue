<template>
  <div>
    <!-- 添加教学楼 -->
    <div class="add-button">
      <el-button type="primary" @click="addTeachbuild">添加</el-button>
    </div>
    <!-- 教学楼列表 -->
    <el-table :data="teachBuildData" size="mini" :stripe="true" :highlight-current-row="true">
      <el-table-column label="序号" type="selection"></el-table-column>
      <!-- <el-table-column prop="id" label="ID"></el-table-column> -->
      <el-table-column prop="teachBuildNo" label="教学楼编号"></el-table-column>
      <el-table-column prop="teachBuildName" label="教学楼名称"></el-table-column>
      <el-table-column prop="teachBuildLocation" label="所属区域"></el-table-column>

      <el-table-column prop="operation" label="操作">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" @click="deleteById(scope.$index, scope.row)">删除</el-button>
          <el-button type="primary" size="mini" @click="editById(scope.$index, scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 弹出表单添加教学楼 -->
    <el-dialog title="添加教学楼" :visible.sync="visibleFormAdd">
      <el-form :model="addFormData" label-position="left" label-width="80px" :rules="editFormRules">
      <el-form-item label="编号">
        <el-input v-model="addFormData.teachBuildNo" autocomplete="off" ></el-input>
      </el-form-item>
      <el-form-item label="名称" prop="teachBuildName">
        <el-input v-model="addFormData.teachBuildName" autocomplete="off" ></el-input>
      </el-form-item>
      <el-form-item label="所在区域" prop="teachBuildLocation">
        <el-input v-model="addFormData.teachBuildLocation" autocomplete="off" ></el-input>
      </el-form-item>
    </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="visibleFormAdd = false">取 消</el-button>
        <el-button type="primary" @click="commit()">提 交</el-button>
      </div>
    </el-dialog>

    <!-- 弹出表单编辑教学楼 -->
    <el-dialog title="编辑教学楼信息" :visible.sync="visibleForm">
      <el-form :model="editFormData" label-position="left" label-width="80px" :rules="editFormRules">
        <el-form-item label="编号">
          <el-input v-model="editFormData.teachBuildNo" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="teachBuildName">
          <!--<el-input v-model="editFormData.teachBuildName" autocomplete="off"></el-input>-->
          <template>
            <el-select v-model="editFormData.teachBuildName" placeholder="名称" clearable="true">
              <el-option
                v-for="item in teachBuildNames"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="所在区域" prop="teachBuildLocation">
          <!--<el-input v-model="editFormData.teachBuildLocation" autocomplete="off"></el-input>-->
          <template>
            <el-select v-model="editFormData.teachBuildLocation" placeholder="所在区域" clearable="true">
              <el-option
                v-for="item in teachBuildLocations"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </template>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="visibleForm = false">取 消</el-button>
        <el-button type="primary" @click="commit()">提 交</el-button>
      </div>
    </el-dialog>

    <!-- 分页 -->
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
</template>

<script>
export default {
  name: "TeachBuildingList",
  data() {
    return {
      teachBuildData: [],
      page: 1,
      pageSize: 10,
      total: 0,
      type: 1, // 编辑
      editFormData: [],
      visibleForm: false,
      addFormData: [],
      visibleFormAdd: false,
      teachBuildLocations: [
        {
          value: "东校区",
          label: "东校区"
        },
        {
          value: "1校区",
          label: "1校区"
        },
        {
          value: "2校区",
          label: "2校区"
        },
        {
          value: "花江校区",
          label: "花江校区"
        },
        {
          value: "东南校区",
          label: "东南校区"
        },
        {
          value: "西南校区",
          label: "西南校区"
        },
        {
          value: "南校区",
          label: "南校区"
        },
        {
          value: "北校区",
          label: "北校区"
        }
      ],
      teachBuildNames: [
        {
          value: "第1教学楼",
          label: "第1教学楼"
        },
        {
          value: "2号教学楼",
          label: "2号教学楼"
        },
        {
          value: "3号教学楼",
          label: "3号教学楼"
        },
        {
          value: "南校区",
          label: "南校区"
        },
        {
          value: "4号教学楼",
          label: "4号教学楼"
        },
        {
          value: "5号教学楼",
          label: "5号教学楼"
        },
        {
          value: "音乐楼",
          label: "音乐楼"
        },
        {
          value: "美术楼",
          label: "美术楼"
        },
        {
          value: "实验楼1",
          label: "实验楼1"
        },
        {
          value: "实验楼2",
          label: "实验楼2"
        },
        {
          value: "逸夫楼1",
          label: "逸夫楼1"
        },
        {
          value: "逸夫楼2",
          label: "逸夫楼2"
        },
        {
          value: "体育楼",
          label: "体育楼"
        },
        {
          value: "南校区",
          label: "南校区"
        },
        {
          value: "化生楼",
          label: "化生楼"
        },
        {
          value: "14号教学楼",
          label: "14号教学楼"
        },
        {
          value: "测试楼",
          label: "测试楼"
        }
      ],
      editFormRules: {
        teachBuildNo: [
           { required: true, message: '请输入教学楼编号', trigger: 'blur' },
        ],
        teachBuildLocation: [
           { required: true, message: '请输入教学楼位置', trigger: 'blur' },
        ],
        teachBuildName: [
           { required: true, message: '请输入教学楼名称', trigger: 'blur' },
        ]
      }
    };
  },

  mounted() {
    this.allTeachBuilding()
  },

  methods: {

    addTeachbuild() {
      this.visibleFormAdd = true
      this.type = 2
      this.addFormData = {}
    },

    /**
     * 提交更新
     */
    commit() {
      console.info("【编辑：1   添加：2】："+this.type);
      if (this.type == 1) {
        // 编辑
        console.info("【编辑：1 】："+this.type);
        this.modifyTeachBuild(this.editFormData)
      } else {
        console.info("【添加：2】："+this.type);
        // type = 2 添加
        this.newteachbuild(this.addFormData)
      }

    },

    // 添加教学楼
    newteachbuild(modifyData) {
      console.log("添加操作："+modifyData);
      this.$axios
        .post("http://localhost:8080/teachbuildinfo/add", modifyData)
        .then(res => {
          if (res.data.code == 0) {
            this.$message({ message: res.data.message, type: "success" })
            this.allTeachBuilding()
            this.visibleFormAdd = false
          } else {
            this.$message.error(res.data.message)
          }
        })
        .catch(error => {
          this.$message.error("更新失败")
        });
    },

    handleSizeChange() {},

    deleteById(index, row) {
      this.deleteTeachBuildingById(row.id)
    },

    editById(index, row) {
      let modifyId = row.id
      this.editFormData = row
      this.visibleForm = true
      this.type = 1
    },

    handleCurrentChange(v) {
      this.page = v;
      this.allTeachBuilding()
    },

    /**
     * 更新教学楼
     */
    modifyTeachBuild(modifyData) {
      this.$axios
        .post("http://localhost:8080/teachbuildinfo/modify/" + this.editFormData.id, modifyData)
        .then(res => {
          if (res.data.code == 0) {
            this.$message({ message: "更新成功", type: "success" })
            this.allTeachBuilding()
            this.visibleForm = false
          } else {
            this.$message.error(res.data.message)
          }
        })
        .catch(error => {
          this.$message.error("更新失败")
        });
    },

    // 获取所有教学楼，带分页
    allTeachBuilding() {
      this.$axios
        .get("http://localhost:8080/teachbuildinfo/list/" + this.page)
        .then(res => {
          if (res.data.code == 0) {
            let ret = res.data.data
            this.teachBuildData = ret.records
            this.total = ret.total
          } else {
            this.$message.error(res.data.message)
          }
        })
        .catch(error => {
          console.log("查询教学楼失败")
        });
    },

    /**
     * 根据ID删除教学楼
     */
    deleteTeachBuildingById(id) {
      this.$axios
        .delete("http://localhost:8080/teachbuildinfo/delete/" + id)
        .then(res => {
          if (res.data.code == 0) {
            this.allTeachBuilding()
            this.$message({message:'删除成功', type: 'success'})
          } else {
            this.$message.error(res.data.message)
          }
        })
        .catch(error => {
          this.$message.error("删除失败")
        });
    }
  }
};
</script>

<style lang="less" scoped>
.footer-button {
  margin-top: 10px;
}

.add-button {
  margin-bottom: 5px;
  text-align: left;
}
</style>

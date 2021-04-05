<template>
  <div>
    <div style="padding:10px 0 10px 0;text-align:left;">
      <el-button type="primary" @click="visibleParentForm = true" size="small">添加父类别</el-button>
      <el-button type="primary" @click="visibleForm = true" size="small">添加子类别</el-button>
    </div>
    <el-table
      size="small"
      :data="tableData"
      style="width: 100%;margin-bottom: 20px;"
      row-key="id"
      border
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column type="index" label="序号"></el-table-column>
      <el-table-column prop="categoryName" label="名称" sortable></el-table-column>
      <el-table-column label="级别">
        <template scope="scope">{{scope.row.parentId == 0 ? '一级类别' : '二级类别'}}</template>
      </el-table-column>
      <el-table-column prop="categoryNo" label="编号"></el-table-column>
      <el-table-column prop="id" label="父ID 【OR】 子ID" sortable>
        <template scope="scope">{{scope.row.parentId == 0 ? '父ID ：' + '【' + scope.row.id + '】': '子ID：' + '【' + scope.row.id + '】'}}</template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column prop="updateTime" label="修改时间"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>

      <el-table-column label="操作" width="150px">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" @click="deleteById(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="添加网课父类别" :visible.sync="visibleParentForm" width="500px">
      <el-form :model="editParentFormData" label-position="left" label-width="100px">
        <el-form-item label="No" prop="categoryNo">
          <el-input v-model="editParentFormData.categoryNo" autocomplete="off" placeholder="请填写类别编号"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="categoryName">
          <el-input v-model="editParentFormData.categoryName" autocomplete="off" placeholder="请填写类别名称"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="editParentFormData.remark" autocomplete="off" placeholder="请填写备注"></el-input>
        </el-form-item>
        <!--        <el-form-item label="父级ID" prop="parentId">-->
        <!--          <el-input v-model="editParentFormData.parentId" autocomplete="off" placeholder="0为一级类别"></el-input>-->
        <!--        </el-form-item>-->
        <el-form-item style="text-align:left;">
          <el-button type="primary" @click="saveParent()" size="small" style="float: right">提 交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="添加网课子类别" :visible.sync="visibleForm" width="500px">
      <el-form :model="editFormData" label-position="left" label-width="100px">
        <el-form-item label="No" prop="categoryNo">
          <el-input v-model="editFormData.categoryNo" autocomplete="off" placeholder="请输入类别编号"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="categoryName">
          <el-input v-model="editFormData.categoryName" autocomplete="off" placeholder="请输入类别名称"></el-input>
        </el-form-item>
        <el-form-item label="选择父类别">
          <el-select class="semester-select-1" v-model="editFormData.parentId" @focus="remoteMethod">
            <el-option
              clearable
              v-for="item in parentIds"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="editFormData.remark" autocomplete="off" placeholder="请填写备注"></el-input>
        </el-form-item>
        <el-form-item style="text-align:left;">
          <el-button type="primary" @click="saveChildren()" size="small" style="float: right">提 交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "OnlineCategory",
  data() {
    return {
      tableData: [],
      parentIds: [],
      visibleParentForm: false,
      visibleForm: false,
      editFormData: {
        categoryNo: null,
        categoryName: null,
        parentId: null
      },
      editParentFormData: {
        categoryNo: null,
        categoryName: null,
        parentId: 0
      }
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.tableData = []
      this.$axios.get("http://localhost:8080/category/one").then(r => {
        console.log(r)
        let c = r.data.data;
        c.map(v => {
          this.$axios
            .get("http://localhost:8080/category/two/" + v.id)
            .then(rr => {
              this.tableData.push({
                id: v.id,
                categoryName: v.categoryName,
                children: rr.data.data,
                categoryNo: v.categoryNo,
                parentId: v.parentId,
                createTime: v.createTime,
                remark: v.remark
              });
            });
        });
      });
    },

    remoteMethod() {
      let params = {
        limit: 100
      };
      this.$axios
        .get("http://localhost:8080/category/one")
        .then(r => {
          let records = r.data.data;
          this.parentIds = [];
          records.map(v => {
            this.parentIds.push({
              label: v.categoryName,
              value: v.id
            });
          });
          this.loading = false;
        });
    },
    //保存父级类别
    saveParent() {
      this.$axios({
        method: "post",
        url: "http://localhost:8080/category/add",
        params: {
          categoryNo: this.editParentFormData.categoryNo,
          categoryName: this.editParentFormData.categoryName,
          parentId: this.editParentFormData.parentId,
          remark: this.editParentFormData.remark
        }
      }).then(r => {
        if (r.data.code == 0) {
          this.$message({ message: "添加成功", type: "success" });
          this.init();
        }
      });
      this.editParentFormData = {};
      this.visibleParentForm = false;
    },

    //保存子类别
    saveChildren() {
      this.$axios({
        method: "post",
        url: "http://localhost:8080/category/add",
        params: {
          categoryNo: this.editFormData.categoryNo,
          categoryName: this.editFormData.categoryName,
          parentId: this.editFormData.parentId,
          remark: this.editFormData.remark
        }
      }).then(r => {
        if (r.data.code == 0) {
          this.$message({ message: "添加成功", type: "success" });
          this.init();
        }
      });
      this.editFormData = {};
      this.visibleForm = false;
    },
    deleteById(v) {
      this.$axios
        .delete("http://localhost:8080/category/delete/" + v.id)
        .then(r => {
          if (r.data.code == 0) {
            this.$message({ message: "删除成功", type: "success" });
            this.init();
          }
        });
    }
  }
};
</script>

<style lang="less" scoped>
.semester-select-1 {
  // vue中下拉框长度的设置
  width:360px!important;
  margin-bottom: 10px;
}
</style>

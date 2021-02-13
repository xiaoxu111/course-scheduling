<template>
  <div>
    <!-- 空教室查询 -->
    <el-select
      v-model="teachbuild.value"
      placeholder="请选择教学楼"
      @change="selectTeachbuild"
      clearable
      class="select-t"
    >
      <el-option
            v-for="item in teachbuild"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
    </el-select>

    <!-- 数据显示 -->
    <el-table :data="classroomData" size="mini" :stripe="true" :highlight-current-row="true">
      <el-table-column label="序号" type="selection"></el-table-column>
      <!-- <el-table-column prop="id" label="ID"></el-table-column> -->
      <el-table-column prop="classRoomNo" label="教室编号"></el-table-column>
      <el-table-column prop="classRoomName" label="教室名"></el-table-column>
      <el-table-column prop="teachbuildNo" label="所属教学楼"></el-table-column>
      <el-table-column prop="capacity" label="容量"></el-table-column>
      <!--<el-table-column prop="remark" label="备注"></el-table-column>-->

      <el-table-column prop="operation" label="操作">
        <template slot-scope="scope">
          <el-button type="success" size="mini" @click="order(scope.$index, scope.row)">预约</el-button>
          <el-button type="success" size="mini" @click="orderDetail(scope.$index, scope.row)">预约详情</el-button>
        </template>
      </el-table-column>
    </el-table>
     <!--分页-->
    <!-- 上一页，当前页，下一页 -->
       <!--<div class="footer-button">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="page"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
        </el-pagination>
      </div>-->
  </div>
</template>

<script>
export default {
  name: "EmptyClassroom",
  data() {
    return {
      teachbuild: [
        {
          value: "",
          label: ""
        }
      ],
      teachbuildNo: "",
      classroomData: [],
      pageSize: 10,
      page: 1,
      total: 0
    };
  },

  mounted() {
    this.queryTeachbuild();
  },

  methods: {

    handleSizeChange() {},

    handleCurrentChange(v) {
      this.page = v
      this.queryByNo(this.teachbuildNo)
    },

    // 预约按钮
    order(index, row) {
      setTimeout(() => {
        this.$message({message: "预约成功", type: "success"})
      }, 1000)
    },
    // 预约详情按钮
    orderDetail(index, row) {
      setTimeout(() => {
        this.$message({message: "预约详情待开发！！", type: "success"})
    }, 1000)
    },
    selectTeachbuild() {
      this.teachbuildNo = this.teachbuild.value
      this.queryByNo(this.teachbuildNo)
    },

    // 根据教学楼编号查询空教室
    queryByNo(teachbuildNo) {
      this.$axios.get("http://localhost:8080/classroom/empty/"+ teachbuildNo)
      .then(res => {
        console.log(res)
        if (res.data.code == 0) {
          let ret = res.data.data
          this.classroomData = ret.records;
          this.total = ret.total
          this.$message({message: "查询出" + " 【 " + ret.total + " 条信息 ！！" + "】 ", type: "success"})
        }
        if (res.data.code == 1){
          this.$message.error(res.data.message);
        }
      })
      .catch(error => {})
    },

    // 查询所有教学楼
    queryTeachbuild() {
      this.$axios
        .get("http://localhost:8080/teachbuildinfo/list")
        .then(res => {
          console.log(res);
          if (res.data.code == 0) {
            let ret = res.data.data;
            this.teachbuild.splice(0, this.teachbuild.length);
            ret.map(v => {
              this.teachbuild.push({
                value: v.teachBuildNo,
                label: v.teachBuildName
              });
            });
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch(error => {});
    }
  }
};
</script>

<style lang="less" scoped>
.select-t {
  text-align: left;
  float: left;
  margin-bottom: 5px;
}

.footer-button {
  margin-top: 10px;
}
</style>

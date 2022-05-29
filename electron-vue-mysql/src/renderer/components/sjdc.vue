<template>
  <el-card>
    <el-button type="success" round @click="InstallUserInfoServlet()"
      >导出所有用户数据</el-button
    >
    <el-button type="success" round @click="InstallGoodsInfoServlet()"
      >导出所有商品数据</el-button
    >
  </el-card>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    InstallUserInfoServlet() {
      this.$confirm("是否将数据库中的用户数据保存到本地？", "提示", {
        confirmButtonText: "保存",
        cancelButtonText: "取消",
        type: "info",
      })
        .then(() => {
          this.$http
            .post(`/api/InstallUserInfoServlet`)
            .then((res) => {
              console.log(res);
              if (res) {
                this.$alert(
                  "文件路径：D:\\所有用户详细信息.xls",
                  "导出成功提醒",
                  {
                    confirmButtonText: "确定",
                  }
                );
              }
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消保存",
          });
        });
    },
    InstallGoodsInfoServlet() {
      this.$confirm("是否将数据库中的商品数据保存到本地？", "提示", {
        confirmButtonText: "保存",
        cancelButtonText: "取消",
        type: "info",
      })
        .then(() => {
          this.$http
            .post(`/api/InstallGoodsInfoServlet`)
            .then((res) => {
              if (res) {
                this.$alert(
                  "文件路径：D:\\所有商品详细信息.xls",
                  "导出成功提醒",
                  {
                    confirmButtonText: "确定",
                  }
                );
              }
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>

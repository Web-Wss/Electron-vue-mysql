<template>
  <el-card class="card-box">
    <div class="header">
      <div class="search">
        <el-input
          v-model="input"
          placeholder="请输入要搜索的 '商品名' 或 '用户昵称'"
        ></el-input>
      </div>
      <el-button @click="searchGoods()" class="searchbtn" type="primary"
        >搜索商品</el-button
      >

      <el-select
        @change="searchSort()"
        v-model="value1"
        placeholder="请选择要查询的分类"
      >
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <el-button class="addGoodsBtn" type="primary" @click="addGoods()"
        >添加商品</el-button
      >
    </div>
    <!-- 列表 -->
    <el-table :data="tableData" style="width: 100%" height="580">
      <el-table-column prop="goods_id" label="商品ID" min-width="80">
      </el-table-column>
      <el-table-column prop="sort_name" label="所属分类" min-width="120">
      </el-table-column>
      <el-table-column prop="goods_name" label="商品名称" min-width="140">
      </el-table-column>
      <el-table-column prop="goods_img" label="商品图片" min-width="140">
        <template slot-scope="scope">
          <div>
            <img style="height: 55px" :src="scope.row.goods_img" />
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="user_nickname"
        label="所属用户（昵称）"
        min-width="130"
      >
      </el-table-column>
      <el-table-column label="操作" min-width="120">
        <template slot-scope="scope">
          <div>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="DeleteGoods(scope.row)"
            ></el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="block" v-show="showfy">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        layout="total, prev, pager, next, jumper"
        :page-size="6"
        :total="goods_total"
      >
      </el-pagination>
    </div>

    <!-- 添加商品 -->
    <el-dialog title="商品发布页面" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="商品名称：" :label-width="formLabelWidth">
          <el-input v-model="form.goods_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品分类：" :label-width="formLabelWidth">
          <template>
            <el-select v-model="value" placeholder="请选择">
              <el-option
                v-for="item in optionss"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="商品价格" :label-width="formLabelWidth">
          <el-input v-model="form.goods_price" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品描述" :label-width="formLabelWidth">
          <el-input v-model="form.goods_describe" autocomplete="off"></el-input>
        </el-form-item>
        <el-upload
          class="upload-demo"
          action="#"
          multiple
          :headers="headers"
          :auto-upload="false"
          :file-list="fileList"
          :on-change="handleChange"
        >
          <el-button size="small" type="primary">点击上传商品图片</el-button>
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件，且不超过500kb
          </div>
        </el-upload>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="upload">发 布</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      // 商品发布
      dialogFormVisible: false,
      // 上传图片列表
      fileList: [],
      // 请求头
      headers: {
        "Content-Type": "multipart/form-data",
      },

      // 上传图片个数
      // dialogFormVisible: false,
      // 商品分类
      value: "",
      value1: "",
      // 用户id
      user_id: 1,
      // 商品名称，商品价格，商品描述
      form: {
        goods_name: "",
        goods_price: "",
        goods_describe: "",
      },
      formLabelWidth: "120px",
      showfy: true,
      currentPage: 1,
      goods_total: 0,
      input: "",
      value: "",
      value1: "",
      optionss: [
        {
          value: "1",
          label: "女士服装",
        },
        {
          value: "2",
          label: "男士服装",
        },
        {
          value: "3",
          label: "美肤护肤",
        },
        {
          value: "4",
          label: "手机数码",
        },
        {
          value: "5",
          label: "食品饮料",
        },
        {
          value: "6",
          label: "珠宝饰品",
        },
        {
          value: "7",
          label: "图书文具",
        },
        {
          value: "8",
          label: "体育用具",
        },
      ],
      options: [
        {
          value: "女生服饰",
          label: "女生服饰",
        },
        {
          value: "男生服饰",
          label: "男生服饰",
        },
        {
          value: "美妆护肤",
          label: "美妆护肤",
        },
        {
          value: "手机数码",
          label: "手机数码",
        },
        {
          value: "食品饮料",
          label: "食品饮料",
        },
        {
          value: "珠宝饰品",
          label: "珠宝饰品",
        },
        {
          value: "图书文具",
          label: "图书文具",
        },
        {
          value: "体育用具",
          label: "体育用具",
        },
      ],
      tableData: [],
    };
  },
  created() {
    // 获取商品总数
    this.getGoodsTotal();
    // 获取商品列表
    this.getGoodsList();
  },
  methods: {
    // 删除商品
    DeleteGoods(goods) {
      this.$confirm("此操作将永久删除该商品，是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.getGoodsTotal();
          this.$http
            .post(`/api/DeleteGoodsServlet?goods_id=${goods.goods_id}`)
            .then((res) => {
              console.log(res);
              if (this.user_total % 6 == 1) {
                console.log("前进1页");
                this.handleCurrentChange(this.currentPage - 1);
              } else {
                console.log("没有前进");
                this.handleCurrentChange(this.currentPage);
              }
              this.getGoodsTotal();
            })
            .catch((err) => {
              console.log(err);
            });
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    // 添加商品
    addGoods() {
      console.log(123);
      this.dialogFormVisible = true;
    }, // 发布按钮
    handleChange(file, fileList) {
      //文件数量改变
      this.fileList = fileList;
    },

    //商品发布按钮
    upload() {
      var param = new FormData();
      this.fileList.forEach((val, index) => {
        param.append("file", val.raw);
      });
      this.$http
        .post(
          `/api/UploadImgServlet?user_id=${this.user_id}&value=${this.value}&goods_name=${this.form.goods_name}&goods_price=${this.form.goods_price}&goods_describe=${this.form.goods_describe}`,
          param
        )
        .then((response) => {
          // 获取商品总数
          this.getGoodsTotal();
          // 获取商品列表
          this.getGoodsList();
        });

      this.$message({
        message: "上传成功!",
        duration: 1000,
      });

      this.dialogFormVisible = false;
    },

    // 分类查询商品
    searchSort() {
      // console.log(this.value);
      this.$http
        .post(`/api/SearchGoodsSortServlet?value=${this.value1}`)
        .then((res) => {
          console.log(res);
          this.tableData = res.data;
          this.$message({
            message: "搜索的结果已经以列表形式展示！",
            type: "success",
          });
          this.showfy = false;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 搜索商品------模糊搜索
    searchGoods() {
      if (this.input == "") {
        this.$message({
          message: "要搜索的值不能为空!!!",
          type: "error",
        });
      } else {
        this.$http
          .post(`/api/SearchGoodsServlet?value=${this.input}`)
          .then((res) => {
            console.log(res);
            this.tableData = res.data;
            this.input = "";
            this.showfy = false;

            this.$message({
              message: "搜索的结果已经以列表形式展示！",
              type: "success",
            });
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      console.log(`当前页: ${val}`);
      this.$http
        .post(`/api/GetGoodsListServlet?currentPage=${val}`)
        .then((res) => {
          console.log(res);
          this.tableData = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    // 得到商品总数
    async getGoodsTotal() {
      const res = await this.$http.get("/api/GoodsTotalServlet");
      this.goods_total = res.data;
    },
    // 获取商品列表
    async getGoodsList() {
      const res = await this.$http.post(
        `/api/GetGoodsListServlet?currentPage=1`
      );
      console.log("res===", res);
      this.tableData = res.data;
    },
  },
};
</script>

<style scoped>
.card-box {
  margin: 0 auto;
  /* height: calc(100vh - 45px); */
}
.header {
  width: 100%;
  height: 40px;
  /* background-color: aqua; */
  display: flex;
  margin-bottom: 15px;
}
.search {
  width: 300px;
  margin-right: 10px;
}
.searchbtn {
  margin-right: 10px;
}

.addGoodsBtn {
  margin-left: 10px;
}
/* 分页 */
.block {
  width: 600px;
  margin: 0 auto;
}
</style>

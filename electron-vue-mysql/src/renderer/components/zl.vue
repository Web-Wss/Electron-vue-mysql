<template>
  <div>
    <el-card class="cardbox">
      <div class="header-all">
        <div class="userall item">
          <div class="usertitle">平台注册总人数</div>
          <div class="userrs">{{ user_total }}人</div>
        </div>
        <!-- <div class="goodsall item">平台商品总数量</div> -->
        <div class="goodsall item">
          <div class="goodstitle">平台商品总数量</div>
          <div class="goodsjs">{{ goods_total }}件</div>
        </div>
        <!-- <div class="fwlall item">平台总访问量</div> -->
        <!-- <div class="fwlall item">
          <div class="fwltitle">平台总访问量</div>
          <div class="fwlfwl">{{ 453287 }}次</div>
        </div> -->
      </div>
      <div class="registernow">
        <div class="title">最新注册人员</div>
        <div class="rigisterlist">
          <div class="li" v-for="(item, i) in registerList" :key="i">
            <img :src="item.user_img" alt="" />
            <div>{{ item.user_nickname }}</div>
          </div>
        </div>
      </div>
      <div id="main" class="echartsDiv">我是图表</div>
    </el-card>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  data() {
    return {
      user_total: 0,
      goods_total: 0,
      // 10个人
      registerList: [
        // {
        //   id: 1,
        //   img: "https://img2.baidu.com/it/u=2234869807,755593456&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        //   title: "我是谁呀？234",
        // },
        // {
        //   id: 2,
        //   img: "https://img0.baidu.com/it/u=4212151755,1031151310&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        //   title: "前端小菜鸡2345",
        // },
        // {
        //   id: 3,
        //   img: "https://img2.baidu.com/it/u=2209097056,2254936076&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        //   title: "我是测试用户哈哈哈",
        // },
        // {
        //   id: 4,
        //   img: "https://img0.baidu.com/it/u=4274595430,3160335357&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        //   title: "我来买买买23423523",
        // },
        // {
        //   id: 5,
        //   img: "https://img2.baidu.com/it/u=1743155949,3677764314&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=499",
        //   title: "你知道什么叫做你知道",
        // },
        // {
        //   id: 6,
        //   img: "https://img1.baidu.com/it/u=3847764214,2704084831&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        //   title: "粉红色丢回复收到",
        // },
        // {
        //   id: 7,
        //   img: "https://img0.baidu.com/it/u=1068176978,1604468940&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        //   title: "我是用户哎235235",
        // },
        // {
        //   id: 8,
        //   img: "https://img1.baidu.com/it/u=1096791796,585870862&fm=253&fmt=auto&app=138&f=JPEG?w=507&h=500",
        //   title: "我终于可以卖自己",
        // },
        // {
        //   id: 9,
        //   img: "https://img1.baidu.com/it/u=1447315970,708763619&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        //   title: "昵称咋这么难起呢",
        // },
        // {
        //   id: 10,
        //   img: "https://img0.baidu.com/it/u=2933804699,3603211044&fm=253&fmt=auto?w=400&h=400",
        //   title: "出好物",
        // },
      ],
    };
  },
  created() {
    this.$http
      .get(`/api/UserTotalServlet`)
      .then((res) => {
        this.user_total = res.data;
      })
      .catch((error) => {
        console.log(error);
      });

    this.$http
      .get(`/api/GoodsTotalServlet`)
      .then((res) => {
        this.goods_total = res.data;
      })
      .catch((error) => {
        console.log(error);
      });

    // 得到最新注册人员
    this.$http
      .get(`/api/GetNewUserServlet`)
      .then((res) => {
        console.log(res);
        this.registerList = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  mounted() {
    this.tubiao();
  },
  methods: {
    tubiao() {
      var chartDom = document.getElementById("main");
      var myChart = echarts.init(chartDom);
      // myChart.resize();
      var option;

      setTimeout(function () {
        option = {
          legend: {},
          tooltip: {
            trigger: "axis",
            showContent: false,
          },
          dataset: {
            source: [
              ["product", "5-21", "5-22", "5-23", "5-24", "5-25", "5-26"],
              ["男生服装", 41, 65, 75, 78, 87, 34],
              ["女士服装", 42, 65, 65, 98, 87, 65],
              ["美妆护肤", 43, 76, 87, 87, 67, 76],
              ["手机数码", 54, 44, 34, 76, 45, 87],
              ["茶酒美食", 12, 76, 76, 75, 78, 98],
              ["珠宝首饰", 54, 56, 98, 34, 65, 78],
              ["图书文具", 32, 23, 45, 45, 87, 67],
              ["体育用品", 24, 65, 87, 65, 87, 56],
            ],
          },
          xAxis: { type: "category" },
          yAxis: { gridIndex: 0 },
          grid: { top: "55%" },
          series: [
            {
              type: "line",
              smooth: true,
              seriesLayoutBy: "row",
              emphasis: { focus: "series" },
            },

            {
              type: "line",
              smooth: true,
              seriesLayoutBy: "row",
              emphasis: { focus: "series" },
            },
            {
              type: "line",
              smooth: true,
              seriesLayoutBy: "row",
              emphasis: { focus: "series" },
            },
            {
              type: "line",
              smooth: true,
              seriesLayoutBy: "row",
              emphasis: { focus: "series" },
            },
            {
              type: "line",
              smooth: true,
              seriesLayoutBy: "row",
              emphasis: { focus: "series" },
            },
            {
              type: "line",
              smooth: true,
              seriesLayoutBy: "row",
              emphasis: { focus: "series" },
            },
            {
              type: "line",
              smooth: true,
              seriesLayoutBy: "row",
              emphasis: { focus: "series" },
            },
            {
              type: "line",
              smooth: true,
              seriesLayoutBy: "row",
              emphasis: { focus: "series" },
            },

            {
              type: "pie",
              id: "pie",
              radius: "30%",
              center: ["50%", "25%"],
              emphasis: {
                focus: "self",
              },
              label: {
                formatter: "{b}: {@5-26} ({d}%)",
              },
              encode: {
                itemName: "product",
                value: "5-26",
                tooltip: "5-26",
              },
            },
          ],
        };
        myChart.on("updateAxisPointer", function (event) {
          const xAxisInfo = event.axesInfo[0];
          if (xAxisInfo) {
            const dimension = xAxisInfo.value + 1;
            myChart.setOption({
              series: {
                id: "pie",
                label: {
                  formatter: "{b}: {@[" + dimension + "]} ({d}%)",
                },
                encode: {
                  value: dimension,
                  tooltip: dimension,
                },
              },
            });
          }
        });
        myChart.setOption(option);
      });
      option && myChart.setOption(option);
    },
  },
};
</script>

<style scoped>
.cardbox {
  height: calc(100vh - 50px);
}

.header-all {
  display: flex;
  column-count: 3;
  /* flex: 1; */
  width: 100%;
  height: 85px;
  /* background-color: aqua; */
}
.item {
  margin: 0 20px;
}
.header-all .userall {
  border-radius: 25px;
  width: 50%;
  height: 100px;
  /* background-color: beige; */
  /* background-image: url("../assets/img/1.webp"); */
  background-position-y: center;
  background: url("../assets/1.png");
}
.usertitle {
  font-size: 30px;
  color: white;
  text-align: center;
}
.userrs {
  text-align: center;
  font-size: 40px;
  color: rgb(233, 70, 41);
}
.header-all .goodsall {
  border-radius: 25px;

  width: 50%;
  height: 100px;
  background-image: url("../assets/2.png");
  background-position-y: center;
}
.goodstitle {
  font-size: 30px;
  color: white;
  text-align: center;
}
.goodsjs {
  text-align: center;

  color: rgb(16, 23, 154);
  font-size: 40px;
}

/* .header-all .fwlall {
  border-radius: 25px;

  width: 30%;
  height: 100px;
  background-image: url("../assets/3.png");
  background-position-y: center;
}
.fwltitle {
  font-size: 30px;
  color: white;
  text-align: center;
}
.fwlfwl {
  text-align: center;

  color: rgb(253, 0, 215);
  font-size: 40px;
} */

/* 注册人 */
.registernow {
  font-size: 13px;
  margin-top: 20px;
  width: 100%;
  height: 140px;
  /* background-color: aquamarine; */
  /* border: 1px solid red; */
  border-bottom: 1px solid red;
  margin-bottom: 5px;
}
.title {
  width: 100%;
  height: 25px;
  /* border-bottom: 1px solid red; */
}

.rigisterlist {
  display: flex;
}
.rigisterlist .li {
  text-align: center;
  /* background-color: antiquewhite; */
  /* border-radius: 50px; */
  flex: 1;
  width: 70px;
  height: 70px;
}

.rigisterlist img {
  border-radius: 50px;
  width: 70px;
}

#main {
  width: 100%;
  height: calc(100vh - 280px);
  /* background-color: blueviolet;s */
}
</style>

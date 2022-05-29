<template>
  <div id="app">
    <el-container>
      <el-aside width="180px">
        <!-- 开启路由模式 -->
        <el-menu :router="true" :default-openeds="['1']">
          <el-menu-item-group>
            <el-menu-item index="/zl"
              ><i class="el-icon-cpu"></i>总览</el-menu-item
            >
            <el-menu-item index="/yhgl"
              ><i class="el-icon-user-solid"></i>用户管理</el-menu-item
            >
            <el-menu-item index="/spgl"
              ><i class="el-icon-s-goods"></i>商品管理</el-menu-item
            >
            <el-menu-item index="/sjdc"
              ><i class="el-icon-menu"></i>数据导出</el-menu-item
            >
          </el-menu-item-group>
          <!-- </el-submenu> -->
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {};
</script>

<style>
body {
  margin: 0;
  padding: 0;
}

el-container {
  position: relative;
}
</style>

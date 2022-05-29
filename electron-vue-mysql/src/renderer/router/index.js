import Vue from "vue";
import Router from "vue-router";

import home from "../components/home.vue";
import zl from "../components/zl.vue";
import yhgl from "../components/yhgl.vue";
import spgl from "../components/spgl.vue";
import sjdc from "../components/sjdc.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "login-page",
      component: require("@/components/login").default,
    },
    {
      path: "/home",
      name: "home",
      component: home,
      children: [
        {
          path: "/zl",
          name: "zl",
          component: zl,
        },
        {
          path: "/yhgl",
          name: "yhgl",
          component: yhgl,
        },
        {
          path: "/spgl",
          name: "spgl",
          component: spgl,
        },
        {
          path: "/sjdc",
          name: "sjdc",
          component: sjdc,
        },
      ],
    },

    {
      path: "*",
      redirect: "/",
    },
  ],
});

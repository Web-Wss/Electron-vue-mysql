package com.msz.controller;

import com.alibaba.fastjson.JSON;
import com.msz.service.GoodsService;
import com.msz.service.GoodsServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SearchGoodsServlet", value = "/SearchGoodsServlet")
public class SearchGoodsServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        搜索商品列表 ------ 模糊搜索
        String value = request.getParameter("value");
        Object result = goodsService.searchGoods(value);
        String jsonArray = JSON.toJSONString(result);
        System.out.println(jsonArray);
        response.getWriter().print(jsonArray);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

package com.msz.controller;

import com.alibaba.fastjson.JSON;
import com.msz.service.GoodsService;
import com.msz.service.GoodsServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "GetGoodsListServlet", value = "/GetGoodsListServlet")
public class GetGoodsListServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        查询商品列表
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        System.out.println(currentPage);
        Object result = goodsService.getGoodsList(currentPage);
        String jsonArray = JSON.toJSONString(result);
        System.out.println(jsonArray);
        response.getWriter().print(jsonArray);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

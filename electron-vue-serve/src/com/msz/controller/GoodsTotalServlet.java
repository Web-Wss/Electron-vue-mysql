package com.msz.controller;

import com.msz.service.GoodsService;
import com.msz.service.GoodsServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "GoodsTotalServlet", value = "/GoodsTotalServlet")
public class GoodsTotalServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        商品总数
        int result = goodsService.goodsTotal();
        response.getWriter().print(result);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

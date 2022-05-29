package com.msz.controller;

import com.msz.service.DeleteGoodsService;
import com.msz.service.DeleteGoodsServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteGoodsServlet", value = "/DeleteGoodsServlet")
public class DeleteGoodsServlet extends HttpServlet {
    DeleteGoodsService deleteGoodsService = new DeleteGoodsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goods_id = request.getParameter("goods_id");
        System.out.println(goods_id);
        String path = this.getServletContext().getRealPath("/statics/imgs");
        System.out.println(path);
//        删除商品
        deleteGoodsService.delete(goods_id,path);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

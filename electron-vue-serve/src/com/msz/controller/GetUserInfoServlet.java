package com.msz.controller;

import com.alibaba.fastjson.JSON;
import com.msz.domain.UserBean;
import com.msz.service.UserService;
import com.msz.service.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "GetUserInfoServlet", value = "/GetUserInfoServlet")
public class GetUserInfoServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        查询用户列表
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        Object result = userService.getUserList(currentPage);
        String jsonArray = JSON.toJSONString(result);
        System.out.println(jsonArray);
        response.getWriter().print(jsonArray);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

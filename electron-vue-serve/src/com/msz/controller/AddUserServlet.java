package com.msz.controller;

import com.msz.domain.UserBean;
import com.msz.service.UserService;
import com.msz.service.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddUserServlet", value = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        添加用户
        String user_username = request.getParameter("user_username");
        String user_nickname = request.getParameter("user_nickname");
        String user_password = request.getParameter("user_password");
        String user_phone = request.getParameter("user_phone");
        String user_qq = request.getParameter("user_qq");

        UserBean userBean = new UserBean();
        userBean.setUser_username(user_username);
        userBean.setUser_nickname(user_nickname);
        userBean.setUser_password(user_password);
        userBean.setUser_phone(user_phone);
        userBean.setUser_qq(user_qq);

        boolean result = userService.addUser(userBean);
        System.out.println(result);
        response.getWriter().print(result);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

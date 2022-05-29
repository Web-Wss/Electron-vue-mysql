package com.msz.controller;

import com.msz.domain.UserBean;
import com.msz.service.UserService;
import com.msz.service.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditUserInfoServlet", value = "/EditUserInfoServlet")
public class EditUserInfoServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取用户id，用户名，昵称，密码
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String user_username = request.getParameter("user_username");
        String user_nickname = request.getParameter("user_nickname");
        String user_password = request.getParameter("user_password");
//        System.out.println(user_id);
//        System.out.println(user_username);
//        System.out.println(user_nickname);
//        System.out.println(user_password);
        UserBean userBean = new UserBean();
        userBean.setUser_id(user_id);
        userBean.setUser_username(user_username);
        userBean.setUser_nickname(user_nickname);
        userBean.setUser_password(user_password);
        boolean result = userService.editUserInfo(userBean);
        System.out.println(result);
        response.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

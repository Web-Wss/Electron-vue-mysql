package com.msz.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.msz.domain.UserBean;
import com.msz.service.UserService;
import com.msz.service.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InstallUserInfoServlet", value = "/InstallUserInfoServlet")
public class InstallUserInfoServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserBean> result = (List<UserBean>) userService.getAllUserList();
        System.out.println(result);

        try {
            List<UserBean> userBeans = new ArrayList<>();

            for (int i = 0; i < result.size(); i ++) {
                userBeans.add(new UserBean(result.get(i).getUser_id(),result.get(i).getUser_username(),result.get(i).getUser_password(),result.get(i).getUser_nickname(),result.get(i).getUser_phone(),result.get(i).getUser_qq(),result.get(i).getUser_wx(),result.get(i).getUser_img(),result.get(i).getUser_status(),result.get(i).getUser_address()));
            }
//            userBeans.add(new UserBean(1,"21121301037","123456","前端菜鸟",1));
//            userBeans.add(new UserBean(1,"21121301037","123456","前端菜鸟","13359074748","1306822660","13359074748","123",1,"123"));
//            userBeans.add(new UserBean(2,"211301038","12345AS6","前QWE端菜鸟","13359074748","1306822660","13359074748","123",1,"123"));
//            userBeans.add(new UserBean(3,"1301039","12343456","前端ASD菜鸟","13359074748","1306822660","13359074748","123",1,"123"));
//            userBeans.add(new UserBean(4,"2112130","1234REW56","前端AF菜鸟","13359074748","1306822660","13359074748","123",1,"123"));
//            userBeans.add(new UserBean(5,"21121037","12G3456","前端AS菜鸟","13359074748","1306822660","13359074748","123",1,"123"));
            List<List<String>> rowAll = new ArrayList<List<String>>();
            String fileName = "所有用户详细信息";
            if (userBeans != null && userBeans.size() > 0) {
                int size = userBeans.size();
//                List<String> row = CollUtil.newArrayList("序号", "学生姓名", "性别", "年龄", "班级","所在地","手机号");
                List<String> row = CollUtil.newArrayList("用户ID", "用户名", "密码", "昵称", "手机","qq","微信","头像地址","能否登录","收获地址");
                rowAll.add(row);
                // 循环添加数据到excel中
                for (int i = 0; i < size; i++) {
                    UserBean user = userBeans.get(i);
//                    List<String> rowItem = CollUtil.newArrayList((i + 1) + "",user.getUser_username(),user.getUser_password(),user.getUser_nickname(),user.getUser_phone(),user.getUser_qq(),user.getUser_wx());
                    List<String> rowItem = CollUtil.newArrayList(String.valueOf(user.getUser_id()),user.getUser_username(),user.getUser_password(),user.getUser_nickname(),user.getUser_phone(),user.getUser_qq(),user.getUser_wx(),user.getUser_img(),String.valueOf(user.getUser_status()),user.getUser_address());
                    rowAll.add(rowItem);
                }
                export(rowAll,fileName);
                response.getWriter().print(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void export(List<List<String>> rowAll, String fileName) throws FileNotFoundException {

        ExcelWriter writer = ExcelUtil.getWriter();

        writer.setColumnWidth(-1, 30);
        writer.setRowHeight(-1,20);
        FileOutputStream output = new FileOutputStream("D:\\"+fileName+".xls");
        // 一次性写出内容
        writer.write(rowAll);
        writer.flush(output);
        // 关闭writer，释放内存
        writer.close();


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

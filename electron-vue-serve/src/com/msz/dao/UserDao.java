package com.msz.dao;

import com.msz.domain.UserBean;

public interface UserDao {
//     用户校验
     UserBean checkUser(String username, String password);

//     得到用户总数
     int userTotal();

//     查询用户列表（分页）
     Object getUserList(int currentPage);

//     修改用户状态
     boolean editUserStatus(int user_id, int status);

//     修改用户信息
     boolean editUserInfo(UserBean userBean);

//     删除用户
     boolean deleteUser(int user_id);

//     添加用户
     boolean addUser(UserBean userBean);

//     搜索用户--模糊搜索
     Object searchUser(String value);

//     得到最小注册人员
     Object getNewUser();

//     查询所有用户
     Object getAllUserList();



}

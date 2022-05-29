package com.msz.service;

import com.msz.domain.UserBean;

public interface UserService {
//    用户校验
    public UserBean checkUser(String username, String password);
//    获取用户总数
    public int userTotal();
//    查询用户列表
    public Object getUserList(int currentPage);

//    修改用户状态
    public boolean editUserStatus(int user_id, int status);

//    修改用户信息
    public boolean editUserInfo(UserBean userBean);

//    删除用户
    public boolean deleteUser(int user_id);

//    添加用户
    public boolean addUser(UserBean userBean);

//    搜索用户
    public Object searchUser(String value);

//    获取最新注册的用户
    public Object getNewUser();

//    查询所有用户
    public Object getAllUserList();

}

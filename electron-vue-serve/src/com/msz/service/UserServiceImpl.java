package com.msz.service;

import com.msz.dao.UserDao;
import com.msz.dao.UserDaoImpl;
import com.msz.domain.UserBean;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public UserBean checkUser(String username, String password) {
        return userDao.checkUser(username,password);
    }

    @Override
    public int userTotal() {
        return userDao.userTotal();
    }

//    查询用户列表（分页）
    @Override
    public Object getUserList(int currentPage) {
        return userDao.getUserList(currentPage);
    }

//    修改用户状态
    @Override
    public boolean editUserStatus(int user_id, int status) {
        return userDao.editUserStatus(user_id,status);
    }

//    修改用户信息
    @Override
    public boolean editUserInfo(UserBean userBean) {
        return userDao.editUserInfo(userBean);
    }

    @Override
    public boolean deleteUser(int user_id) {
        return userDao.deleteUser(user_id);
    }

    @Override
    public boolean addUser(UserBean userBean) {
        return userDao.addUser(userBean);
    }

    @Override
    public Object searchUser(String value) {
        return userDao.searchUser(value);
    }

    @Override
    public Object getNewUser() {
        return userDao.getNewUser();
    }

    @Override
    public Object getAllUserList() {
        return userDao.getAllUserList();
    }

//    删除用户

}

package com.msz.dao;

import com.msz.domain.UserBean;
import com.msz.utils.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
//    用户校验
    public UserBean checkUser(String username, String password) {
        UserBean userBean = new UserBean();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = C3P0Util.getConnection();
            String sql = "select  * from user where user_username = ? && user_password = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            if (rs.next()) {
                userBean.setUser_nickname(rs.getString("user_nickname"));
            }
            return userBean;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm,rs);
        }
        return userBean;
    }

    @Override
//    得到用户总数
    public int userTotal() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int i = 0;
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT COUNT(*) FROM USER;";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                i = rs.getInt(1);
            }
            return i;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm,rs);
        }
        return i;
    }

    @Override
//    查询用户列表（分页）
    public List<UserBean> getUserList(int currentPage) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<UserBean> list = new ArrayList<>();
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT * FROM USER  LIMIT ?,?;";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,(currentPage*8)-8);
            pstm.setInt(2,8);
            rs = pstm.executeQuery();
            while (rs.next()) {
//                userBean.setUser_id(rs.getInt("user_id"));
//                userBean.setUser_username(rs.getString("user_username"));
//                userBean.setUser_nickname(rs.getString("user_nickname"));
//                userBean.setUser_password(rs.getString("user_password"));
//                userBean.setUser_status(rs.getString("user_status"));
                list.add(new UserBean(rs.getInt("user_id"),rs.getString("user_username"),rs.getString("user_nickname"),rs.getString("user_password"),rs.getInt("user_status")));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm,rs);
        }
        return list;
    }

//    修改用户状态
    @Override
    public boolean editUserStatus(int user_id, int status) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int i = 0;
        try {
            conn = C3P0Util.getConnection();
            String sql = "update user set user_status = ? where user_id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,status);
            pstm.setInt(2,user_id);
            i = pstm.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm);
        }
        return false;
    }

//    修改用户信息
    @Override
    public boolean editUserInfo(UserBean userBean) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int i = 0;
        try {
            conn = C3P0Util.getConnection();
            String sql = "update user set user_username = ?,user_nickname = ?,user_password = ? where user_id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,userBean.getUser_username());
            pstm.setString(2,userBean.getUser_nickname());
            pstm.setString(3,userBean.getUser_password());
            pstm.setInt(4,userBean.getUser_id());
            i = pstm.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm);
        }
        return false;
    }

//    删除用户
    @Override
    public boolean deleteUser(int user_id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int i = 0;
        try {
            conn = C3P0Util.getConnection();
            String sql = "delete from user where  user_id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,user_id);
            i = pstm.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm);
        }
        return false;
    }

//    添加用户
    @Override
    public boolean addUser(UserBean userBean) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int i = 0;
        try {
            conn = C3P0Util.getConnection();
            String sql = "insert into user(user_username,user_nickname,user_password,user_phone,user_qq) values (?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,userBean.getUser_username());
            pstm.setString(2,userBean.getUser_nickname());
            pstm.setString(3,userBean.getUser_password());
            pstm.setString(4,userBean.getUser_phone());
            pstm.setString(5,userBean.getUser_qq());
            i = pstm.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm);
        }
        return false;
    }

//    根据用户输入的值模糊搜索
    @Override
    public List<UserBean> searchUser(String value) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<UserBean> list = new ArrayList<>();
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT * FROM USER WHERE CONCAT(user_username,user_nickname) LIKE "+ "\'%" + value + "%\'";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new UserBean(rs.getInt("user_id"),rs.getString("user_username"),rs.getString("user_nickname"),rs.getString("user_password"),rs.getInt("user_status")));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm,rs);
        }
        return list;
    }

    @Override
    public Object getNewUser() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<UserBean> list = new ArrayList<>();
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT * FROM USER ORDER BY user_id DESC LIMIT 10";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new UserBean(rs.getInt("user_id"),rs.getString("user_username"),rs.getString("user_nickname"),rs.getString("user_password"),rs.getInt("user_status"),rs.getString("user_img")));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm,rs);
        }
        return list;
    }

//    得到所有用户数据
    @Override
    public Object getAllUserList() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<UserBean> list = new ArrayList<>();
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT * FROM USER;";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
//                userBean.setUser_id(rs.getInt("user_id"));
//                userBean.setUser_username(rs.getString("user_username"));
//                userBean.setUser_nickname(rs.getString("user_nickname"));
//                userBean.setUser_password(rs.getString("user_password"));
//                userBean.setUser_status(rs.getString("user_status"));
                list.add(new UserBean(rs.getInt("user_id"),rs.getString("user_username"),rs.getString("user_password"),rs.getString("user_nickname"),rs.getString("user_phone"),rs.getString("user_qq"),rs.getString("user_wx"),rs.getString("user_img"),rs.getInt("user_status"),rs.getString("user_address")));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm,rs);
        }
        return list;
    }
}

package com.msz.dao;

import com.msz.domain.GoodsBean;
import com.msz.utils.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao{
    @Override
//    获取商品总数
    public int goodsTotal() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int i = 0;
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT COUNT(*) FROM Goods;";
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

//    获取商品列表（分页）
    @Override
    public List<GoodsBean> getGoodsList(int currentPage) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<GoodsBean> list = new ArrayList<>();
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT  a.*, b.*, c.*, d.user_nickname FROM goods a  JOIN goods_img b  ON a.`goods_id`=b.`goods_id` JOIN goods_sort c ON a.`sort_id` = c.sort_id  JOIN USER d ON a.`user_id` = d.user_id GROUP BY a.goods_id LIMIT ?,?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,(currentPage*6)-6);
            pstm.setInt(2,6);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new GoodsBean(rs.getInt("goods_id"),rs.getString("sort_name"),rs.getString("goods_name"),rs.getString("goods_img"),rs.getString("user_nickname")));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn, pstm,rs);
        }
        return list;
    }

//    搜索商品------模糊搜索
    @Override
    public Object searchGoods(String value) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<GoodsBean> list = new ArrayList<>();
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT  a.*, b.*, c.*, d.user_nickname FROM goods a  JOIN goods_img b  ON a.`goods_id`=b.`goods_id`  JOIN goods_sort c ON a.`sort_id` = c.sort_id  JOIN USER d ON a.`user_id` = d.user_id WHERE CONCAT(a.`goods_name`,d.`user_nickname`)  LIKE "+ "\'%" +value + "%'" + " GROUP BY a.goods_id;";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new GoodsBean(rs.getInt("goods_id"),rs.getString("sort_name"),rs.getString("goods_name"),rs.getString("goods_img"),rs.getString("user_nickname")));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

//    搜索商品分类下的商品
    @Override
    public Object searchGoodsSort(String value) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<GoodsBean> list = new ArrayList<>();
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT  a.*, b.*, c.*, d.user_nickname FROM goods a  JOIN goods_img b  ON a.`goods_id`=b.`goods_id`  JOIN goods_sort c ON a.`sort_id` = c.sort_id  JOIN USER d ON a.`user_id` = d.user_id WHERE c.sort_name  = "+ "\'" +value + "'" + " GROUP BY a.goods_id;";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new GoodsBean(rs.getInt("goods_id"),rs.getString("sort_name"),rs.getString("goods_name"),rs.getString("goods_img"),rs.getString("user_nickname")));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Object getAllGoodsList() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<GoodsBean> list = new ArrayList<>();
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT  a.*, b.*, c.*, d.user_nickname FROM goods a  JOIN goods_img b  ON a.`goods_id`=b.`goods_id` JOIN goods_sort c ON a.`sort_id` = c.sort_id  JOIN USER d ON a.`user_id` = d.user_id";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new GoodsBean(rs.getInt("goods_id"),rs.getString("sort_name"),rs.getString("goods_name"),rs.getString("goods_price"),rs.getString("goods_describe"),rs.getString("goods_img"),rs.getString("user_nickname")));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn, pstm,rs);
        }
        return list;
    }
}

package com.msz.dao;

import com.msz.domain.GoodsBean;
import com.msz.utils.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteGoodsDaoImpl implements DeleteGoodsDao{
    @Override
    public  List<GoodsBean> getGoodsImg(String goods_id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<GoodsBean> list = new ArrayList<>();
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT goods_img FROM goods_img WHERE goods_id = " + goods_id;
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new GoodsBean(rs.getString("goods_img")));
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
    public void deleteGoodsImg(String goods_id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = C3P0Util.getConnection();
            String sql = "delete from goods_img where goods_id = " + goods_id;
            pstm = conn.prepareStatement(sql);
            pstm.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm,rs);
        }
    }

    @Override
    public void deleteGoods(String goods_id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = C3P0Util.getConnection();
            String sql = "delete from goods where goods_id = " + goods_id;
            pstm = conn.prepareStatement(sql);
            pstm.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm,rs);
        }
    }
}

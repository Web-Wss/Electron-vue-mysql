package com.msz.dao;

import com.msz.domain.AddGoodsImg;
import com.msz.domain.GoodsBean;
import com.msz.utils.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadImgDaoImpl implements UploadImgDao{
    @Override
    public int GoodsMaxId() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int GoodsMaxId = 0;
        try {
            conn = C3P0Util.getConnection();
            String sql = "SELECT MAX(goods_id) FROM goods;";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                GoodsMaxId = rs.getInt(1);
            }
            return GoodsMaxId;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm,rs);
        }
        return GoodsMaxId;
    }

    @Override
    public boolean addGoods(GoodsBean goodsBean) {
        int goods_id = goodsBean.getGoods_id();
        int sort_id = goodsBean.getSort_id();
        String goods_name = goodsBean.getGoods_name();
        String goods_price = goodsBean.getGoods_price();
        String goods_describe = goodsBean.getGoods_describe();
        Date time = goodsBean.getGoods_time();
        int user_id = goodsBean.getUser_id();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String goods_time = sdf.format(time);

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = C3P0Util.getConnection();
            String sql = "insert into goods values(" + "\'"+goods_id +"\'"+"," +"\'"+ sort_id+ "\'"+ ","+"\'"+goods_name+ "\'"+","+"\'"+goods_price+ "\'"+","+"\'"+goods_describe+ "\'"+","+"\'"+goods_time+ "\'"+","+"\'"+user_id + "\'"+ ")";
            pstm = conn.prepareStatement(sql);
            int b = pstm.executeUpdate();
            if (b > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm);
        }
        return false;
    }

    @Override
    public void addImg(AddGoodsImg addGoodsImg) {
        String[] goods_img = addGoodsImg.getGoods_img();
        int goods_id = addGoodsImg.getGoods_id();
        Connection conn = null;
        PreparedStatement pstm = null;
        boolean b = true;
        try {
            conn = C3P0Util.getConnection();
            for (int i = 0; i < goods_img.length; i ++) {
                String sql = "insert into goods_img (goods_id,goods_img) values("+goods_id+","+ "\'" + "http://localhost:8080/electron_vue_server/statics/imgs/" + goods_img[i]+"\'"+")";
                pstm = conn.prepareStatement(sql);
                pstm.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            C3P0Util.release(conn,pstm);
        }
    }
}

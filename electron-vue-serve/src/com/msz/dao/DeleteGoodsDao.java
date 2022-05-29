package com.msz.dao;

import com.msz.domain.GoodsBean;

import java.util.List;

public interface DeleteGoodsDao {
//    查询当前商品的所有图片
List<GoodsBean> getGoodsImg(String goods_id);

//        删除数据库中img表中的记录
    void deleteGoodsImg(String goods_id);
//        删除goods表中的记录
    void deleteGoods(String goods_id);
}

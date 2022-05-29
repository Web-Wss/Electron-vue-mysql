package com.msz.dao;

public interface GoodsDao {
//    获取商品总数
    int goodsTotal();

//    获取商品列表（分页）
    Object getGoodsList(int currentPage);

//    搜索商品------模糊搜索
    Object searchGoods(String value);

//    搜索商品------分类
    Object searchGoodsSort(String value);

//    获取所有商品列表
    Object getAllGoodsList();
}

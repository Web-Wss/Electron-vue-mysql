package com.msz.service;

import com.msz.domain.GoodsBean;

public interface GoodsService {
//    获取商品总数
    public int goodsTotal();

//    获取商品列表
    public Object getGoodsList(int currentPage);

//    搜索商品------模糊搜索
    public Object searchGoods(String value);

//    搜索商品------分类
    public  Object searchGoodsSort(String value);

//    获取所有商品列表
    public Object getAllGoodsList();
}

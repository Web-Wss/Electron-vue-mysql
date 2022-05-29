package com.msz.service;

import com.msz.dao.GoodsDao;
import com.msz.dao.GoodsDaoImpl;

public class GoodsServiceImpl implements GoodsService{
    private GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
//    商品总数
    public int goodsTotal() {
        return goodsDao.goodsTotal();
    }

    //    获取商品列表
    @Override
    public Object getGoodsList(int currentPage) {
        return goodsDao.getGoodsList(currentPage);
    }

    @Override
    public Object searchGoods(String value) {
        return goodsDao.searchGoods(value);
    }

    @Override
    public Object searchGoodsSort(String value) {
        return goodsDao.searchGoodsSort(value);
    }

    @Override
    public Object getAllGoodsList() {
        return goodsDao.getAllGoodsList();
    }


}

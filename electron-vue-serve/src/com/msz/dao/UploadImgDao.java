package com.msz.dao;

import com.msz.domain.AddGoodsImg;
import com.msz.domain.GoodsBean;

public interface UploadImgDao {
    //    获取数据库商品比编号最大值
     int GoodsMaxId();
    //    向数据库中添加商品信息
     boolean addGoods(GoodsBean goodsBean);

    //    添加商品图片到数据库中
     void addImg(AddGoodsImg addGoodsImg);
}

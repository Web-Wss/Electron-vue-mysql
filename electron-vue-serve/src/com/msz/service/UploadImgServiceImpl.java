package com.msz.service;

import com.msz.dao.UploadImgDao;
import com.msz.dao.UploadImgDaoImpl;
import com.msz.domain.AddGoodsImg;
import com.msz.domain.GoodsBean;

public class UploadImgServiceImpl implements UploadImgService{
    private UploadImgDao uploadImgDao = new UploadImgDaoImpl();

    @Override
    public int GoodsMaxId() {
        return uploadImgDao.GoodsMaxId();
    }

    @Override
    public boolean addGoods(GoodsBean goodsBean) {
        return uploadImgDao.addGoods(goodsBean);
    }

    @Override
    public void addImg(AddGoodsImg addGoodsImg) {
        uploadImgDao.addImg(addGoodsImg);
    }
}

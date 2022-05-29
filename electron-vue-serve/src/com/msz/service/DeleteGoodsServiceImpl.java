package com.msz.service;

import com.msz.dao.DeleteGoodsDao;
import com.msz.dao.DeleteGoodsDaoImpl;
import com.msz.domain.GoodsBean;

import java.io.File;
import java.util.List;

public class DeleteGoodsServiceImpl implements DeleteGoodsService{
    private DeleteGoodsDao deleteGoodsDao = new DeleteGoodsDaoImpl();
    @Override
    public void delete(String goods_id, String path) {
        //        查询商品图片列表，删除服务器中的图片
        List<GoodsBean> list = deleteGoodsDao.getGoodsImg(goods_id);
        list.stream().forEach(goods_img->{
//            System.out.println(path + goods_img.getGoods_img());
            String imgs = goods_img.getGoods_img();
            System.out.println(path +"\\"+ imgs.substring(imgs.lastIndexOf("/")+1));
            File delImg = new File(path + "\\"+ imgs.substring(imgs.lastIndexOf("/")+1));
            if (delImg.exists()) {
                delImg.delete();
            }
        });
        deleteGoodsDao.deleteGoodsImg(goods_id);
        deleteGoodsDao.deleteGoods(goods_id);
    }
}

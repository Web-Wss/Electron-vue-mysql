package com.msz.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.msz.domain.GoodsBean;
import com.msz.service.GoodsService;
import com.msz.service.GoodsServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InstallGoodsInfoServlet", value = "/InstallGoodsInfoServlet")
public class InstallGoodsInfoServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GoodsBean> result = (List<GoodsBean>) goodsService.getAllGoodsList();
        System.out.println(result);
        try {
            List<GoodsBean> goodsBeans = new ArrayList<>();

            for (int i = 0; i < result.size(); i ++) {
                goodsBeans.add(new GoodsBean(result.get(i).getGoods_id(),result.get(i).getSort_name(),result.get(i).getGoods_name(),result.get(i).getGoods_price(),result.get(i).getGoods_describe(),result.get(i).getGoods_img(),result.get(i).getUser_nickname()));
            }
            List<List<String>> rowAll = new ArrayList<List<String>>();
            String fileName = "所有商品详细信息";
            if (goodsBeans != null && goodsBeans.size() > 0) {
                int size = goodsBeans.size();
                List<String> row = CollUtil.newArrayList("商品ID","所属分类","商品名称","商品价格","商品描述","商品图片地址","发布者昵称");
                rowAll.add(row);
                //            循环添加数据到excel中
                for (int i = 0; i < size; i ++) {
                    GoodsBean goods =  goodsBeans.get(i);
                    List<String> rowItem = CollUtil.newArrayList(String.valueOf(goods.getGoods_id()),goods.getSort_name(),goods.getGoods_name(),goods.getGoods_price(),goods.getGoods_describe(),goods.getGoods_img(),goods.getUser_nickname());
                    rowAll.add(rowItem);
                }
                export(rowAll,fileName);
                response.getWriter().print(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void export(List<List<String>> rowAll, String fileName) throws FileNotFoundException {
        ExcelWriter writer = ExcelUtil.getWriter();

        writer.setColumnWidth(-1,30);
        writer.setRowHeight(-1,20);
        FileOutputStream output = new FileOutputStream("D:\\"+fileName+".xls");
        // 一次性写出内容
        writer.write(rowAll);
        writer.flush(output);
        // 关闭writer，释放内存
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

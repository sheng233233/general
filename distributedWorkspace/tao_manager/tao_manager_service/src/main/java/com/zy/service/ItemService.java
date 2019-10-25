package com.zy.service;

import com.zy.common.pojo.EUDataGridResult;
import com.zy.common.pojo.TaotaoResult;
import com.zy.pojo.TbItem;

public interface ItemService {

    // 根据id查询商品
    public TbItem getItemById(Long id);

    //查询商品列表 //http://localhost:8080/item/list
    public EUDataGridResult getItemList(Integer page,Integer rows);


    //添加新的商品
    public TaotaoResult createItem(TbItem item, String desc);



}

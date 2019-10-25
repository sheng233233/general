package com.zy.rest.service;

import com.zy.common.pojo.TaotaoResult;

public interface ItemService {

    //查询商品信息 -- 基本信息
    public TaotaoResult getItemBaseInfo(Long id);

    //查询商品信息 -- 描述信息
    public TaotaoResult getItemDescInfo(Long id);
}

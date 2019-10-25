package com.zy.portal.service;

import com.zy.portal.pojo.ItemInfo;

public interface ItemService {

    //取商品基本信息
    public ItemInfo getItemInfo(Long itemId);
    //获取商品详情
    public String getItemDesc(Long itemId);



}

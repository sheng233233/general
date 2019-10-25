package com.zy.portal.service.impl;


import com.zy.common.pojo.TaotaoResult;
import com.zy.common.utils.HttpClientUtil;
import com.zy.pojo.TbItemDesc;
import com.zy.portal.pojo.ItemInfo;
import com.zy.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ItemServiceImpl implements ItemService {


    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_BASE_URL}")
    private String ITEM_BASE_URL;
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;


    @Override
    public ItemInfo getItemInfo(Long itemId) {

        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_BASE_URL + itemId);
        //将json转化为ItemInfo对象,并赋值给result的data属性中
        TaotaoResult result = TaotaoResult.formatToPojo(json, ItemInfo.class);
        //取出data
        ItemInfo itemInfo = (ItemInfo) result.getData();
        return itemInfo;
    }

    @Override
    public String getItemDesc(Long itemId) {
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);

        TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemDesc.class);
        if (result.getStatus() == 200){
            TbItemDesc itemDesc = (TbItemDesc) result.getData();
            return itemDesc.getItemDesc();
        }
        return null;
    }
}

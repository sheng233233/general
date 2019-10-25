package com.zy.portal.service.impl;

import com.zy.common.utils.HttpClientUtil;
import com.zy.common.utils.JsonUtils;
import com.zy.common.pojo.TaotaoResult;
import com.zy.pojo.TbContent;
import com.zy.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContentServiceImpl implements ContentService {
    //取出resource.properties中的数据

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;

    @Override
    public String getContentAD() {
        //根据请求获得json数据
        String data = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
        //将json类型的data转化成TBContent类型的list集合
        TaotaoResult taotaoResult = TaotaoResult.formatToList(data, TbContent.class);

        //数据就在result的data中
        List<TbContent> data1 = (List<TbContent>) taotaoResult.getData();
        //将数据取出来,放在一个符合前段要求的键取值的集合中

        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();

        for (TbContent tbContent:data1) {
            HashMap<String, Object> result = new HashMap<>();
            //设置第一张图片的信息
            result.put("src", tbContent.getPic());
            result.put("height", 240);
            result.put("width", 670);
            //设置第一张图片的信息
            result.put("srcB", tbContent.getPic2());
            result.put("widthB", 550);
            result.put("heightB", 240);
            //广告链接
            result.put("href", tbContent.getUrl());
            //图片找不到时的警告
            result.put("alt", tbContent.getSubTitle());

            resultList.add(result);
        }

        String json = JsonUtils.objectToJson(resultList);

        return json;
    }
}

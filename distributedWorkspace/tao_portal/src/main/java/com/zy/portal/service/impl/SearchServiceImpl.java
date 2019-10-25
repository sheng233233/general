package com.zy.portal.service.impl;

import com.zy.common.pojo.TaotaoResult;
import com.zy.common.utils.HttpClientUtil;
import com.zy.portal.pojo.SearchResult;
import com.zy.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SearchServiceImpl implements SearchService {

    @Value("${QUERY_URL}")
    private String QUERY_URL;

    @Override
    public SearchResult searchItem(String query,int page,int rows) {
        //用于封装返回
        SearchResult searchResult = new SearchResult();

        //为请求封装参数
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("q",query);
        hashMap.put("page",page+"");
        hashMap.put("rows",rows+"");

        //调用服务,执行查询    http://localhost:8083/search/query
        String json = HttpClientUtil.doGet(QUERY_URL,hashMap);
        //将查询结果的字符串转化为TaotaoResult对象的data属性
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
        if (taotaoResult.getStatus() == 200){
            return (SearchResult) taotaoResult.getData();
        }


        return null;
    }
}

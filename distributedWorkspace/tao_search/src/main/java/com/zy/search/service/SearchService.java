package com.zy.search.service;

import com.zy.search.pojo.SearchResult;

public interface SearchService {

    //商品搜索
    public SearchResult searchItem(String query,Integer page,Integer rows);


}

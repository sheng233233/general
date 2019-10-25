package com.zy.portal.service;

import com.zy.portal.pojo.SearchResult;

public interface SearchService {

    public SearchResult searchItem(String query, int page, int rows);

}

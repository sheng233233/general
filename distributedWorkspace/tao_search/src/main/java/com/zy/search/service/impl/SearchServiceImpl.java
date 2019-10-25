package com.zy.search.service.impl;

import com.zy.search.dao.ItemDao;
import com.zy.search.pojo.SearchResult;
import com.zy.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchServiceImpl implements SearchService {

    @Autowired
    ItemDao itemDao;

    @Override
    public SearchResult searchItem(String query, Integer page, Integer rows) {
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条件
        solrQuery.setQuery(query);
        //相当于设置limit的第一个参数
        solrQuery.setStart((page-1)*rows);
        //相当于设置limit的第二个参数
        solrQuery.setRows(rows);
        //设置搜索域
        solrQuery.set("df", "item_keywords");

        //调用查询
        SearchResult searchResult = itemDao.getSearch(solrQuery);

        //设置当前页
        searchResult.setCurPage(page);
        //设置总页数
        Long totalPage = (long)Math.ceil(1.0*searchResult.getRecordCount()/rows);
        searchResult.setPageCount(totalPage);

        return searchResult;
    }
}

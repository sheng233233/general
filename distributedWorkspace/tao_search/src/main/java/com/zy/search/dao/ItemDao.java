package com.zy.search.dao;

import com.zy.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface ItemDao {
    //从solr索引库中查询数据
    public SearchResult getSearch(SolrQuery solrQuery);

}

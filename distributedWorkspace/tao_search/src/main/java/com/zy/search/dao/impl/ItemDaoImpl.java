package com.zy.search.dao.impl;

import com.zy.search.dao.ItemDao;
import com.zy.search.pojo.Item;
import com.zy.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class ItemDaoImpl implements ItemDao {

    @Autowired
    SolrServer solrServer;

    @Override
    public SearchResult getSearch(SolrQuery solrQuery) {
        //填充该对象 返回
        SearchResult searchResult = new SearchResult();

        try {
            //执行查询
            QueryResponse query = solrServer.query(solrQuery);
            //获得查询结果
            SolrDocumentList solrDocumentList = query.getResults();
            //设置总条数
            searchResult.setRecordCount(solrDocumentList.getNumFound());

            LinkedList<Item> items = new LinkedList<>();
            for (SolrDocument document: solrDocumentList) {
                //准备item对象
                Item item = new Item();
                //从document对象中取出属性,赋值给item对象
                item.setId(document.get("id").toString());
                item.setTitle(document.get("item_title").toString());
                item.setSell_point(document.get("item_sell_point").toString());
                item.setPrice(Long.parseLong(document.get("item_price").toString()));
                item.setImage(document.get("item_image").toString());
                item.setCategory_name(document.get("item_category_name").toString());
                // item_desc 存储为false 因此会是null
//                item.setItem_des(document.get("item_desc").toString());
                //将item对象添加至数据集合
                items.add(item);
            }
            //设置数据集合
            searchResult.setItemList(items);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return searchResult;
    }
}

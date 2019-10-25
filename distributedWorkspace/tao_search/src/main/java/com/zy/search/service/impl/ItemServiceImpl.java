package com.zy.search.service.impl;

import com.zy.common.pojo.TaotaoResult;
import com.zy.search.mapper.ItemMapper;
import com.zy.search.pojo.Item;
import com.zy.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    SolrServer solrServer;


    @Override
    public TaotaoResult importAllItem() { //查询数据库转存solr的索引库
        //查询数据
        List<Item> itemInfo = itemMapper.getItemInfo();
        //使用solr的api将查询到的数据存放在索引库中
        for (Item item: itemInfo) {
            //利用solr文档对象将相应信息填入配置文件中设置的相应字段
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",item.getId());
            document.setField("item_title",item.getTitle());
            document.setField("item_sell_point",item.getSell_point());
            document.setField("item_price",item.getPrice());
            document.setField("item_image",item.getImage());
            document.setField("item_category_name",item.getCategory_name());
            document.setField("item_desc",item.getItem_des());

            try {
                solrServer.add(document);
            } catch (Exception e) {
                e.printStackTrace();
                return TaotaoResult.build(500,"document对象添加失败");
            }
        }

        try {
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,"solr提交失败");
        }

        return TaotaoResult.ok();

    }
}

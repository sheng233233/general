package com.zy.rest.service.impl;

import com.zy.common.utils.JsonUtils;
import com.zy.mapper.TbContentMapper;
import com.zy.pojo.TbContent;
import com.zy.pojo.TbContentExample;
import com.zy.rest.redis.dao.JedisDao;
import com.zy.rest.service.ContentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContentServiceImpl implements ContentService {
    private Logger logger;

    @Autowired
    TbContentMapper tcm;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    String INDEX_CONTENT_REDIS_KEY;

    @Autowired
    JedisDao jd;


    @Override
    public List<TbContent> getTbContent(Long categoryId) {

        try {
            //从redis中取值
            String hget = jd.hget(INDEX_CONTENT_REDIS_KEY, categoryId + "");
            if (hget != null){//redis中有值
                List<TbContent> tbContents = JsonUtils.jsonToList(hget, TbContent.class);
//                System.out.println("从redis中取得");
                return tbContents;
            }
        }catch (Exception e){
//            System.out.println("缓存失效");
        }


        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        //设置条件
        criteria.andCategoryIdEqualTo(categoryId);

        List<TbContent> selectByExample = tcm.selectByExample(tbContentExample);
        System.out.println("从数据库中取值");

        //存入redis
        try {//键的设置要有层次感  选hash值
            //把数据转成字符串
            String objectToJson = JsonUtils.objectToJson(selectByExample);
            //(INDEX_CONTENT_REDIS_KEY,(89,xxxxxxxxxxx))  主页
            jd.hset(INDEX_CONTENT_REDIS_KEY,categoryId+"",objectToJson);
            System.out.println("将数据存入redis");


        }catch (Exception e){
            System.out.println("缓存中没有这个值");
        }

        return selectByExample;
    }
}

package com.zy.rest.service.impl;

import com.zy.common.pojo.TaotaoResult;
import com.zy.rest.redis.dao.JedisDao;
import com.zy.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceImpl implements RedisService {

    @Autowired
    JedisDao jd;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public TaotaoResult synCache(String categoryId) {
        jd.hdel(INDEX_CONTENT_REDIS_KEY,categoryId+"");
        System.out.println("已经进行缓存同步,删除旧缓存");
        return TaotaoResult.ok();
    }
}

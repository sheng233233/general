package com.zy.rest.service;

import com.zy.common.pojo.TaotaoResult;

public interface RedisService { //redis缓存同步

    public TaotaoResult synCache(String categoryId);



}

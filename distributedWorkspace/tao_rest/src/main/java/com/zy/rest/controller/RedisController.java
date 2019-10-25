package com.zy.rest.controller;

import com.zy.common.pojo.TaotaoResult;
import com.zy.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/syn/cache")
public class RedisController {//缓存同步

    @Autowired
    RedisService rs;

    @ResponseBody
    @RequestMapping("/content/{categoryId}")
    public TaotaoResult synCache(@PathVariable String categoryId){
        return rs.synCache(categoryId);
    }

}

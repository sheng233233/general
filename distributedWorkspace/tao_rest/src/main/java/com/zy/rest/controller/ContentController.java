package com.zy.rest.controller;

import com.zy.common.utils.ExceptionUtil;
import com.zy.common.pojo.TaotaoResult;
import com.zy.pojo.TbContent;
import com.zy.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService cs;

    //发布服务,restFul风格
    @RequestMapping("/list/{categoryId}")
    public TaotaoResult getContentById(@PathVariable Long categoryId){
        try {
            List<TbContent> list = cs.getTbContent(categoryId);
            return TaotaoResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

    }

//    /rest/itemCat/all
}

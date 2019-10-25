package com.zy.search.controller;

import com.zy.common.pojo.TaotaoResult;
import com.zy.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @ResponseBody
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public TaotaoResult searchItem(String q, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows){
        if (q != null && !"".equals(q))
        try {
            q = new String(q.getBytes("ISO-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(searchService.searchItem(q,page,rows));
    }




}

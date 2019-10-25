package com.zy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PageController {


    //跳转至首页
    @RequestMapping("/")
    public String index(){
        return "index";
    }


    @RequestMapping("/{page}")
    public String otherPage(@PathVariable String page){
        return page;
    }


}

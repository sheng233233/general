package com.zy.search.controller;

import com.zy.common.pojo.TaotaoResult;
import com.zy.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manager")
public class ItemController {


    @Autowired
    ItemService itemService;


    @ResponseBody
    @RequestMapping("/importall")
    public TaotaoResult importAll(){
        return itemService.importAllItem();
    }



}

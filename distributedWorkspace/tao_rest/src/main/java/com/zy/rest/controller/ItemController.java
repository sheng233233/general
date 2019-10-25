package com.zy.rest.controller;

import com.zy.common.pojo.TaotaoResult;
import com.zy.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {


    @Autowired
    ItemService itemService;

    @ResponseBody
    @RequestMapping("/info/{itemId}")    //  /rest/item/info/{itemId}
    public TaotaoResult getItemBaseInfo(@PathVariable Long itemId){
        return itemService.getItemBaseInfo(itemId);
    }

    @ResponseBody
    @RequestMapping("/desc/{itemId}")    //  /rest/item/info/{itemId}
    public TaotaoResult getItemDescInfo(@PathVariable Long itemId){
        return itemService.getItemDescInfo(itemId);
    }


}

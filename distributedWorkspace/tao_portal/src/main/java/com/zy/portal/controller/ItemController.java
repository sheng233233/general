package com.zy.portal.controller;

import com.zy.portal.pojo.ItemInfo;
import com.zy.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ItemController {

    @Autowired
    ItemService itemService;


    //http://localhost:8082/item/

    @RequestMapping("/item/{itemId}")
    public String getItem(@PathVariable Long itemId, Model model){
        ItemInfo itemInfo = itemService.getItemInfo(itemId);
        model.addAttribute("item",itemInfo);
        return "item";
    }

    @ResponseBody
    @RequestMapping(value = "/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    public String getItemDesc(@PathVariable Long itemId){
        return itemService.getItemDesc(itemId);
    }


}

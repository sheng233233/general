package com.zy.controller;

import com.zy.common.pojo.EUDataGridResult;
import com.zy.common.pojo.TaotaoResult;
import com.zy.pojo.TbItem;
import com.zy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /*//测试方法
    @ResponseBody
    @RequestMapping("/{itemId}")
    public TbItem getItemById(@PathVariable long itemId){ //从路径中取值
        System.out.println("进来了getItemById");
        TbItem ibItem = itemService.getItemById(itemId);
        return ibItem;
    }*/

    @ResponseBody
    @RequestMapping("/list")
    public EUDataGridResult getItemList(Integer page,Integer rows){
        return itemService.getItemList(page,rows);
    }

    @ResponseBody
    @RequestMapping("/save")
    public TaotaoResult insertItem(TbItem tbItem,String desc){
        TaotaoResult result = itemService.createItem(tbItem, desc);

        return result;

    }

    @ResponseBody
    @RequestMapping("edit")
    public TaotaoResult showEdit(@RequestParam("_") Long itemId){
        System.out.println(itemId);
        TbItem item = itemService.getItemById(itemId);
        return TaotaoResult.ok(item);
    }


}

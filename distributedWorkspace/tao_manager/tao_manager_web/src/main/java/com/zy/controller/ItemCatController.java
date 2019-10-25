package com.zy.controller;

import com.zy.common.pojo.TreeNode;
import com.zy.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {//商品类目
    @Autowired
    ItemCatService ics;

    @ResponseBody
    @RequestMapping("/list")
    public List<TreeNode> getItemCatList(@RequestParam(defaultValue = "0") long id){
        List<TreeNode> catList = ics.getCatList(id);
        return catList;

    }

}

package com.zy.controller;


import com.zy.common.pojo.EUDataGridResult;
import com.zy.common.pojo.TaotaoResult;
import com.zy.common.pojo.TreeNode;
import com.zy.pojo.TbContent;
import com.zy.service.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentCategoryController {
    @Autowired
    ContentService ccs;





    @ResponseBody
    @RequestMapping("/category/list")
    public List<TreeNode> getContentCategory(@RequestParam(defaultValue ="0") long id){
        return ccs.getContentCateglory(id);
    }
    @ResponseBody
@RequestMapping("/category/create")
    public TaotaoResult insertContentCategory(long parentId,String name){

        return ccs.create(parentId,name);
    }
    @ResponseBody
    @RequestMapping("/query/list")
    public EUDataGridResult getContentList(int page, int rows, long categoryId){
        EUDataGridResult contentById = ccs.getContentById(page, rows, categoryId);
        return contentById;

    }
    @ResponseBody
    @RequestMapping("/save")
    public TaotaoResult insertContent(TbContent tbContent) {
        return ccs.addContent(tbContent);
    }
}

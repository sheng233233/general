package com.zy.service;

import com.zy.common.pojo.EUDataGridResult;
import com.zy.common.pojo.TreeNode;
import com.zy.common.pojo.TaotaoResult;
import com.zy.pojo.TbContent;

import java.util.List;

public interface ContentService {
    //查询内容分类列表
    public List<TreeNode> getContentCateglory(Long id);
    //添加
    public TaotaoResult create(long parentId, String name);
    //内容查询
    public EUDataGridResult getContentById(Integer page, Integer rows, Long categoryId);
    //内容添加
    public TaotaoResult addContent(TbContent tbContent);




}

package com.zy.service;

import com.zy.common.pojo.TreeNode;

import java.util.List;

/**
 * 商品类目相关的service
 */
public interface ItemCatService {

    public List<TreeNode> getCatList(Long parentId);
}

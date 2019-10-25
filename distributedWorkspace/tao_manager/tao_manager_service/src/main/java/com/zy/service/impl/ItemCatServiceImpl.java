package com.zy.service.impl;

import com.zy.common.pojo.TreeNode;
import com.zy.mapper.TbItemCatMapper;
import com.zy.pojo.TbItemCat;
import com.zy.pojo.TbItemCatExample;
import com.zy.pojo.TbItemExample;
import com.zy.service.ItemCatService;
import com.zy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    TbItemCatMapper icm;

    @Override
    public List<TreeNode> getCatList(Long parentId) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> selectByExample = icm.selectByExample(tbItemCatExample);
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        for (TbItemCat tbItemCat:selectByExample) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(tbItemCat.getId());
            treeNode.setText(tbItemCat.getName());
            treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            arrayList.add(treeNode);
        }
        return arrayList;
    }
}

package com.zy.rest.service.impl;

import com.zy.common.pojo.ItemCat;
import com.zy.common.pojo.ItemCatResult;
import com.zy.mapper.TbItemCatMapper;
import com.zy.pojo.TbItemCat;
import com.zy.pojo.TbItemCatExample;
import com.zy.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;


    @Override
    public ItemCatResult queryAllCategory() throws Exception {

        ItemCatResult result = new ItemCatResult();
        result.setData(getItemCatList(0));

        return result;
    }

    /**
     * 查询分类列表
     */
    private List<?> getItemCatList(long parentid) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //查询parentid为0的分类信息
        criteria.andParentIdEqualTo(parentid);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List dataList = new ArrayList();
        for (TbItemCat tbItemCat : list) {
            //判断是否为父节点
            if (tbItemCat.getIsParent()) {
                ItemCat itemCat = new ItemCat();
                itemCat.setUrl("/category/" + tbItemCat.getId() + ".html");
                itemCat.setName(tbItemCat.getName());
                //递归调用
                itemCat.setItem(getItemCatList(tbItemCat.getId()));
                //添加到列表
                dataList.add(itemCat);
            } else {
                String catItem = "/item/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
                dataList.add(catItem);
            }
        }
        return dataList;
    }


}

package com.zy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.common.pojo.EUDataGridResult;
import com.zy.common.utils.IDUtils;
import com.zy.common.pojo.TaotaoResult;
import com.zy.mapper.TbItemDescMapper;
import com.zy.mapper.TbItemMapper;
import com.zy.pojo.TbItem;
import com.zy.pojo.TbItemDesc;
import com.zy.pojo.TbItemExample;
import com.zy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    TbItemMapper itemMapper;

    @Autowired
    TbItemDescMapper itemDescMapper;

    @Override
    public TbItem getItemById(Long id) {
        //TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //添加查询条件
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria createCriteria = example.createCriteria();
        createCriteria.andIdEqualTo(id);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            TbItem item = list.get(0);
            return item;
        }

        return null;

    }

    @Override
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        //逆向工程+pagehelp

        TbItemExample tbItemExample = new TbItemExample();
        //------------
        //pagehelper在执行前设置  当前页和每页条数
        PageHelper.startPage(page,rows);

        //------------

        List<TbItem> selectByExample = itemMapper.selectByExample(tbItemExample);//执行
        PageInfo<TbItem> pageInfo = new PageInfo<>(selectByExample);

//        System.out.println("-----------------------");
//        for (TbItem i:selectByExample) {
//            System.out.println(i.getId());
//        }
//        System.out.println("-----------------------");
//        System.out.println(pageInfo.getTotal());

        EUDataGridResult euDataGridResult = new EUDataGridResult();

        //得到查询结果
        euDataGridResult.setRows(selectByExample);
        //得到总条数
        euDataGridResult.setTotal(pageInfo.getTotal());



        return euDataGridResult;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc) {
        //通过工具类生成商品id
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //设置商品状态  1正常 2下架 3删除
        item.setStatus((byte)1);
        //设置插入与更新的时间
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向数据库插入商品数据
        itemMapper.insert(item);

        //实例化商品描述对象
        TbItemDesc tbItemDesc = new TbItemDesc();
        //设置描述
        tbItemDesc.setItemDesc(desc);
        //设置对应的商品信息
        tbItemDesc.setItemId(itemId);
        //设置创建时间
        tbItemDesc.setCreated(new Date());
        //设置更新时间
        tbItemDesc.setUpdated(new Date());
        //向数据库插入商品描述信息
        itemDescMapper.insert(tbItemDesc);
        //返回成功信息
        return TaotaoResult.ok();
    }
}

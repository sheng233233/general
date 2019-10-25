package com.zy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.common.pojo.EUDataGridResult;
import com.zy.common.pojo.TreeNode;
import com.zy.common.pojo.TaotaoResult;
import com.zy.common.utils.HttpClientUtil;
import com.zy.mapper.TbContentCategoryMapper;
import com.zy.mapper.TbContentMapper;
import com.zy.pojo.TbContent;
import com.zy.pojo.TbContentCategory;
import com.zy.pojo.TbContentCategoryExample;
import com.zy.pojo.TbContentExample;
import com.zy.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentCategoryMapper tccm;

    @Autowired
    TbContentMapper tcm;



    @Override
    public List<TreeNode> getContentCateglory(Long id) {
        //根据id查询content

        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> selectByExample = tccm.selectByExample(tbContentCategoryExample);
        List<TreeNode> list = new LinkedList<>();

        for (TbContentCategory cc:selectByExample) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(cc.getId());
            treeNode.setText(cc.getName());
            treeNode.setState(cc.getIsParent()?"closed":"opend");

            list.add(treeNode);

        }

        return list;
    }

    @Override
    public TaotaoResult create(long parentId, String name) {
        //添加分类

        TbContentCategory tbContentCategory = new TbContentCategory();
        //主键自增
        tbContentCategory.setId(null);
        //设置parentid
        tbContentCategory.setParentId(parentId);
        //设置分类名称
        tbContentCategory.setName(name);
        //设置是否为父节点,默认false
        tbContentCategory.setIsParent(false);
        //设置添加时间
        tbContentCategory.setCreated(new Date());
        //是指修改时间
        tbContentCategory.setUpdated(new Date());
        //设置状态
        tbContentCategory.setStatus(1);
        //设置顺序
        tbContentCategory.setSortOrder(1);

        //插入
        tccm.insert(tbContentCategory); //插入的同时,查询出自增的id并赋值给tbContentCategory对象的id

        //修改父节点的isparent为true
        TbContentCategory parent = new TbContentCategory();
        parent.setId(tbContentCategory.getParentId());
        parent.setIsParent(true);
        tccm.updateByPrimaryKeySelective(parent);

        return TaotaoResult.ok(tbContentCategory);
    }

    @Override
    public EUDataGridResult getContentById(Integer page, Integer rows, Long categoryId) {
        //根据分类id(categoryId)获取分类下广告内容(content表)

        //查询content表,因此使用tbContentExample
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        //设置条件
        criteria.andCategoryIdEqualTo(categoryId);
        //设置分页
        PageHelper.startPage(page,rows);
        //查询内容
        List<TbContent> selectByExample = tcm.selectByExample(tbContentExample);
        //包装成符合eui的数据(结果:rows  条数:total)并返回
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(selectByExample);
        PageInfo<TbContent> pageInfo = new PageInfo<>(selectByExample);
        result.setTotal(pageInfo.getTotal());

        return result;

    }

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_SYNC}")
    private String REST_CONTENT_SYN;


    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        //添加分类下的广告

        //补全信息,主键自增
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());

        tcm.insert(tbContent);
        //调用  http://localhost:8081/rest/syn/cache/content/?? 请求,实现缓存同步
        HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYN+tbContent.getCategoryId());

        return TaotaoResult.ok();
    }


}

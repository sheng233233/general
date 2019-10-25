package com.zy.rest.service;

import com.zy.pojo.TbContent;

import java.util.List;

public interface ContentService {

    //根据id查询分类内容
    public List<TbContent> getTbContent(Long categoryId);

}

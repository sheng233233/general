package com.zy.rest.controller;

import com.zy.common.pojo.ItemCatResult;
import com.zy.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/itemcat")
public class itemcatController {

    //  /rest/itemcat/all?callback=category.getDataService

    @Autowired
    ItemCatService itemCatService;


    @ResponseBody
    @RequestMapping("all")
    public MappingJacksonValue queryAll(String callback) throws Exception {
        //查询分类列表
        ItemCatResult result = itemCatService.queryAllCategory();
        //包装jsonp
        MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
        //设置包装的回调方法名
        jacksonValue.setJsonpFunction(callback);

        return jacksonValue;

    }


}

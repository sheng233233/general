package com.zy.portal.controller;

import com.zy.portal.pojo.SearchResult;
import com.zy.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    //http://localhost:8082/search.html?q=条件
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "q") String query,@RequestParam(defaultValue = "30") Integer rows,@RequestParam(defaultValue="1")Integer page, Model model) throws UnsupportedEncodingException {
        if (query != null){
            query = new String(query.getBytes("iso-8859-1"),"utf-8");
        }

        SearchResult searchResult = searchService.searchItem(query, page, rows);


        //向页面传递参数
        model.addAttribute("query", query);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);

        return "search";

    }


}

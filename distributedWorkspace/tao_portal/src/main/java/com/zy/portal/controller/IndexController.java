package com.zy.portal.controller;

import com.zy.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    ContentService cs;

//    @RequestMapping("/index")
//    public String showIndex(Model model){
//        String adJson = cs.getContentAD();
//        model.addAttribute("ad1", adJson);
//        return "index";
//    }

    @RequestMapping("/index")
    public ModelAndView showIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ad1",cs.getContentAD());
        modelAndView.setViewName("index");
        return modelAndView;
    }


}

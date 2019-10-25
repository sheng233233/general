package com.zy.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    //跳转至注册页面
    @RequestMapping("/register")
    public String toRegister(){
        return "register";
    }

    //跳转至登录页面
    @RequestMapping("/login")
    public String toLogin(String redirect, Model model){
        model.addAttribute("redirect",redirect);
        return "login";
    }




}

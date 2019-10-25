package com.zy.sso.controller;

import com.zy.common.pojo.TaotaoResult;
import com.zy.pojo.TbUser;
import com.zy.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/check/{param}/{type}", method = RequestMethod.GET)
    public TaotaoResult checkUser(@PathVariable String param, @PathVariable Integer type) throws Exception {
        if (param != null){
            param = new String(param.getBytes("iso-8859-1"),"utf-8");
        }
        return userService.checkData(param,type);
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public TaotaoResult register(TbUser user){
        return userService.registUser(user);
    }

    //跳转至登录页面
    @RequestMapping("/showLogin")
    public String showLogin(String redirect, Model model){
        model.addAttribute("model",model);
        return "login";
    }

    //跳转至登录页面
    @RequestMapping("/showRegister")
    public String showRegister(String redirect, Model model){
        model.addAttribute("model",model);
        return "register";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TaotaoResult login(HttpServletRequest request, HttpServletResponse response, String username, String password){
        return userService.login(request,response,username,password);
    }

    @ResponseBody
    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    public Object findToken(@PathVariable String token,String callback){
        return userService.findByToken(token,callback);
    }

    @ResponseBody
    @RequestMapping(value = "/logout/{token}", method = RequestMethod.GET)
    public Object logout(@PathVariable String token,String callback){
        return userService.logout(token,callback);
    }

}

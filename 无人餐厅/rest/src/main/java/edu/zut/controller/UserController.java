package edu.zut.controller;

import edu.zut.entity.User;
import edu.zut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/home")
public class UserController {

    @Autowired
    UserService us;

    public String encryption(String text){
        String cipher1 = DigestUtils.md5DigestAsHex(text.getBytes());  //md5加密
        String cipher2 = new StringBuffer(DigestUtils.md5DigestAsHex(text.getBytes())).reverse().substring(0,6);//取反截取6位
        String cipher = DigestUtils.md5DigestAsHex(cipher2.getBytes()); //再次MD5加密
        return cipher;
    }

    @RequestMapping("/login")
    public ModelAndView login(User user){
        ModelAndView mv = new ModelAndView();
        user.setPwd(encryption(user.getPwd()));
        if(us.findByNameAndPwd(user) != null){
            mv.setViewName("admin");
        }else {
            mv.setViewName("login");
            mv.addObject("message","用户名或密码错误");
        }
        return mv;
    }



}

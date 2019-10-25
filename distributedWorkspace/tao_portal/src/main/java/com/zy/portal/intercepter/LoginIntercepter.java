package com.zy.portal.intercepter;

import com.zy.common.pojo.TaotaoResult;
import com.zy.common.utils.CookieUtils;
import com.zy.common.utils.HttpClientUtil;
import com.zy.pojo.TbUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;

public class LoginIntercepter implements HandlerInterceptor {

    @Value("${SSO_BASE_URL}")
    String SSO_BASE_URL;
    @Value("${FIND_USER}")
    String FIND_USER;
    @Value("${TO_LOGIN}")
    String TO_LOGIN;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //访问前拦截
        //确认是否登录[已登录--> ]
        //从cookie中取出token,去redis中查找是否有用户
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        //访问sso的getuserbytoken,获得用户

        String json = HttpClientUtil.doGet("http://localhost:8084/user/token/"+token);
//        String json = HttpClientUtil.doGet(SSO_BASE_URL+FIND_USER+token);

        try {
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
            TbUser user = (TbUser) result.getData();
            String name = user.getUsername();
            return true;
        }catch (Exception e){

            //未登录,跳转至登录页面
            //不合格---将用户带到登录页面 --把当前请求的路径也传过去

//            response.sendRedirect(""+SSO_BASE_URL+TO_LOGIN+request.getRequestURL());
//            HttpClientUtil.doGet(SSO_BASE_URL+TO_LOGIN+request.getRequestURL());
            response.sendRedirect("http://localhost:8084/page/login?redirect="+request.getRequestURL());

            return false;
        }




    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    } //登录拦截器,未经登录,不允许查看商品详情页面



}

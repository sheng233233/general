package com.zy.sso.service;

import com.zy.common.pojo.TaotaoResult;
import com.zy.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    //数据校验
    public TaotaoResult checkData(String param, Integer type);

    //用户注册
    public TaotaoResult registUser(TbUser user);

    //用户登录
    public TaotaoResult login(HttpServletRequest request, HttpServletResponse response, String username, String password);

    //根据令牌查询redis缓存中是否存在user对象
    public Object findByToken(String token, String callback);

    //安全退出
    public Object logout(String token, String callback);
}

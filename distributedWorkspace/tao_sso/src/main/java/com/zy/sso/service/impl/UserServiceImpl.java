package com.zy.sso.service.impl;

import com.zy.common.pojo.TaotaoResult;
import com.zy.common.utils.CookieUtils;
import com.zy.common.utils.JsonUtils;
import com.zy.mapper.TbUserMapper;
import com.zy.pojo.TbUser;
import com.zy.pojo.TbUserExample;
import com.zy.sso.dao.JedisDao;
import com.zy.sso.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserMapper userMapper;
    @Autowired
    JedisDao jedisDao;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private int SSO_SESSION_EXPIRE;


    private String encryption(String password){
        String cipher1 = DigestUtils.md5DigestAsHex(password.getBytes());  //md5加密
        String cipher2 = new StringBuffer(cipher1).reverse().substring(0,6);//取反截取6位
        String cipher = DigestUtils.md5DigestAsHex(cipher2.getBytes()); //再次MD5加密
        return cipher;
    }


    @Override
    public TaotaoResult checkData(String param, Integer type) {

        TbUserExample userExample = new TbUserExample();

        TbUserExample.Criteria criteria = userExample.createCriteria();


        switch (type){
            case 1:{
                criteria.andUsernameEqualTo(param);
                break;
            }
            case 2:{
                criteria.andPhoneEqualTo(param);
                break;
            }
            case 3:{
                criteria.andEmailEqualTo(param);
                break;
            }
            default:{
                System.out.println("类型错误");
                return TaotaoResult.ok(false);
            }
        }

        List<TbUser> userList = userMapper.selectByExample(userExample);
        if (userList != null && userList.size() > 0){
            return TaotaoResult.ok(false);
        }

        return TaotaoResult.ok(true);
    }

    @Override
    public TaotaoResult registUser(TbUser user) {
        //1 验证
        boolean flag1 = (boolean) checkData(user.getUsername(), 1).getData();
        boolean flag2 = (boolean) checkData(user.getPhone(), 2).getData();
//        boolean flag3 = (boolean) checkData(user.getEmail(), 3).getData();

        if (flag1&&flag2){
            //三者都不重复,则添加

            //补全信息
            user.setCreated(new Date());
            user.setUpdated(new Date());
            user.setPassword(encryption(user.getPassword()));

            //插入
            try {
                userMapper.insert(user);
                return TaotaoResult.build(200,"注册成功");
            }catch (Exception e){
                return TaotaoResult.build(500,"注册失败");
            }

        }

        return TaotaoResult.build(400,"用户名或密码或手机已经存在,注册失败");
    }

    @Override
    public TaotaoResult login(HttpServletRequest request, HttpServletResponse response,String username, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(encryption(password));

        List<TbUser> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0){
            //可以查到
            //登录成功
            //生成token
            String token = UUID.randomUUID().toString();
            //封装token返回
            TaotaoResult result = TaotaoResult.ok(token);
            //将查询得到的user对象转化为json
            String json = JsonUtils.objectToJson(users.get(0));
            try {
                //存入redis
                jedisDao.set(REDIS_USER_SESSION_KEY+token,json);
                //设置失效时间
                jedisDao.expire(REDIS_USER_SESSION_KEY+token,SSO_SESSION_EXPIRE);

                //将token写入cookie  使用时从cookie中取出TT_TOKEN的值查找redis
                CookieUtils.setCookie(request,response,"TT_TOKEN",token);


//                System.out.println("存入redis");
            }catch (Exception e){
                System.out.println("缓存炸了");
            }


            return result;
        }
        //7f180dc1-5e22-42a3-b75a-e10e59a0a867

        return TaotaoResult.build(500,"用户名或密码错误,登录失败");
    }

    @Override
    public Object findByToken(String token, String callback) {
        TaotaoResult tt = new TaotaoResult();
        String json = jedisDao.get(REDIS_USER_SESSION_KEY+token);
        if (json == null || "".equals(json)){
            tt = TaotaoResult.build(500,"redis中不存在该用户,请登录");
        }else {
            try {
                //转成user对象
                TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
                //重置过期时间
                jedisDao.expire(REDIS_USER_SESSION_KEY+token,SSO_SESSION_EXPIRE);
//                System.out.println("从redis中获得");
                tt = TaotaoResult.ok(user);
            }catch (Exception e){
                System.out.println("缓存炸了!!!");
            }
        }


        if(null != callback && !"".equals(callback)){
            //进行jsonp包装
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(tt);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;  //将返回值类型设置为object 扩大兼容性

        }

        return tt;
    }

    @Override
    public Object logout(String token, String callback) {
        TaotaoResult result = new TaotaoResult();
        //清空session(redis)
        try {
            jedisDao.del(REDIS_USER_SESSION_KEY+token);
            result = TaotaoResult.ok();
        }catch (Exception e){
            System.out.println("缓存炸了!!");
            result = TaotaoResult.build(500,"缓存炸了,退出失败");
        }

        if (null != callback && !"".equals(callback)){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }
}

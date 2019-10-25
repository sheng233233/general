package com.zy.rest.service.impl;

import com.zy.common.pojo.TaotaoResult;
import com.zy.common.utils.JsonUtils;
import com.zy.mapper.TbItemDescMapper;
import com.zy.mapper.TbItemMapper;
import com.zy.pojo.TbItem;
import com.zy.pojo.TbItemDesc;
import com.zy.rest.redis.dao.JedisDao;
import com.zy.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    TbItemMapper itemMapper;
    @Autowired
    TbItemDescMapper itemDescMapper;
    @Autowired
    JedisDao jedisDao;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private int REDIS_ITEM_EXPIRE;


    @Override
    public TaotaoResult getItemBaseInfo(Long id) {

        //查询缓存
        String json = jedisDao.get(REDIS_ITEM_KEY + id+":info");
        if (json!=null && !"".equals(json)){
            try {
                System.out.println("从缓存中取出商品基本信息");
                return TaotaoResult.formatToPojo(json, TbItem.class);
            }catch (NullPointerException e){
                System.out.println(e);
            }
        }
        //若缓存中没有则,
        //查询数据库
        TbItem item = itemMapper.selectByPrimaryKey(id);
        //封装
        TaotaoResult result = TaotaoResult.ok(item);
        try {
            //加入缓存
            String item2redis = JsonUtils.objectToJson(result);
            jedisDao.set(REDIS_ITEM_KEY + id+":info",item2redis);
            System.out.println("将商品基本信息存入缓存");
            //设置过期时间
            jedisDao.expire(REDIS_ITEM_KEY + id+":info",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            System.out.println("缓存崩溃");
        }

        //返回
        return result;
    }

    @Override
    public TaotaoResult getItemDescInfo(Long id) {
        //查询缓存
        String json = jedisDao.get(REDIS_ITEM_KEY + id+":desc");
        if (json!=null && !"".equals(json)){
            try {
                System.out.println("从缓存中取出商品描述信息");
                return TaotaoResult.formatToPojo(json, TbItemDesc.class);
            }catch (NullPointerException e){
                System.out.println(e);
            }
        }
        //若缓存中没有则,
        //查询数据库
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
        //封装
        TaotaoResult result = TaotaoResult.ok(itemDesc);
        try {
            //加入缓存
            String item2redis = JsonUtils.objectToJson(result);
            jedisDao.set(REDIS_ITEM_KEY + id+":desc",item2redis);
            System.out.println("将商品描述信息存入缓存");
            //设置过期时间
            jedisDao.expire(REDIS_ITEM_KEY + id+":desc",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            System.out.println("缓存崩溃");
        }

        //返回
        return result;
    }


}

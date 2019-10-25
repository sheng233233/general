package com.zy.portal.service.impl;

import com.zy.common.pojo.TaotaoResult;
import com.zy.common.utils.CookieUtils;
import com.zy.common.utils.HttpClientUtil;
import com.zy.common.utils.JsonUtils;
import com.zy.pojo.TbItem;
import com.zy.portal.pojo.CartItem;
import com.zy.portal.pojo.Item;
import com.zy.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartServiceImpl implements CartService {


    @Value("${REST_BASE_URL}")
    String REST_BASE_URL;
    @Value("${ITEM_BASE_URL}")
    String ITEM_BASE_URL;


    @Override
    public TaotaoResult addCartItem(HttpServletRequest request, HttpServletResponse response, Long ItemId, Integer num) {


        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_BASE_URL + ItemId);
        TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
        TbItem item = (TbItem) result.getData();
        //先从cookie中取出购物车列表
        String tt_cart = CookieUtils.getCookieValue(request, "TT_CART", true);
        if (tt_cart == null || "".equals(tt_cart)){
            //表示之前没有购物车列表(第一次添加至购物车)
            ArrayList<CartItem> cartItemList = new ArrayList<>();

            CartItem cartItem = new CartItem();
            cartItem.setId(item.getId());
            cartItem.setImage(item.getImage());
            cartItem.setPrice(item.getPrice());
            cartItem.setTitle(item.getTitle());
            cartItem.setNum(num);
            cartItemList.add(cartItem);

            String objectToJson = JsonUtils.objectToJson(cartItemList);

            CookieUtils.setCookie(request,response,"TT_CART",objectToJson,true);
            return TaotaoResult.ok();
        }else {
            //表示之情已经存在过购物车
            List<CartItem> cartItemList = JsonUtils.jsonToList(tt_cart, CartItem.class);

            for (CartItem cartItem: cartItemList) {
                if (cartItem.getId() == item.getId()){
                    //发现购物车中存在历史商品
                    //修改数量
                    cartItem.setNum(cartItem.getNum()+num);
                    System.out.println(cartItem.getNum());
                    String objectToJson = JsonUtils.objectToJson(cartItemList);
                    CookieUtils.setCookie(request,response,"TT_CART",objectToJson,true);

                    return TaotaoResult.ok();//结束
                }

            }
            //购物车中不存在该商品
            //添加购物车列表
            CartItem cartItem = new CartItem();
            cartItem.setId(item.getId());
            cartItem.setImage(item.getImage());
            cartItem.setPrice(item.getPrice());
            cartItem.setTitle(item.getTitle());
            cartItem.setNum(num);

            cartItemList.add(cartItem);

            String objectToJson = JsonUtils.objectToJson(cartItemList);
            CookieUtils.setCookie(request,response,"TT_CART",objectToJson,true);

            return TaotaoResult.ok();
        }
    }

    @Override
    public TaotaoResult updateNum(HttpServletRequest request, HttpServletResponse response, Long ItemId, Integer num) {


        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_BASE_URL + ItemId);
        TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
        TbItem item = (TbItem) result.getData();
        //先从cookie中取出购物车列表
        String tt_cart = CookieUtils.getCookieValue(request, "TT_CART", true);
        if (tt_cart == null || "".equals(tt_cart)){
            //表示之前没有购物车列表(第一次添加至购物车)
            ArrayList<CartItem> cartItemList = new ArrayList<>();

            CartItem cartItem = new CartItem();
            cartItem.setId(item.getId());
            cartItem.setImage(item.getImage());
            cartItem.setPrice(item.getPrice());
            cartItem.setTitle(item.getTitle());
            cartItem.setNum(num);
            cartItemList.add(cartItem);

            String objectToJson = JsonUtils.objectToJson(cartItemList);

            CookieUtils.setCookie(request,response,"TT_CART",objectToJson,true);
            return TaotaoResult.ok();
        }else {
            //表示之情已经存在过购物车
            List<CartItem> cartItemList = JsonUtils.jsonToList(tt_cart, CartItem.class);

            for (CartItem cartItem: cartItemList) {
                if (cartItem.getId() == item.getId()){
                    //发现购物车中存在历史商品
                    //修改数量
                    cartItem.setNum(num);
                    String objectToJson = JsonUtils.objectToJson(cartItemList);
                    CookieUtils.setCookie(request,response,"TT_CART",objectToJson,true);

                    return TaotaoResult.ok();//结束
                }

            }
            //购物车中不存在该商品
            //添加购物车列表
            CartItem cartItem = new CartItem();
            cartItem.setId(item.getId());
            cartItem.setImage(item.getImage());
            cartItem.setPrice(item.getPrice());
            cartItem.setTitle(item.getTitle());
            cartItem.setNum(num);

            cartItemList.add(cartItem);

            String objectToJson = JsonUtils.objectToJson(cartItemList);
            CookieUtils.setCookie(request,response,"TT_CART",objectToJson,true);

            return TaotaoResult.ok();
        }
    }

    @Override
    public TaotaoResult delete(HttpServletRequest request, HttpServletResponse response, Long ItemId) {
        String tt_cart = CookieUtils.getCookieValue(request, "TT_CART",true);
        List<CartItem> cartItemList = JsonUtils.jsonToList(tt_cart, CartItem.class);
        for (CartItem cartItem: cartItemList) {
            if (cartItem.getId() == ItemId){
                //删除该商品
                cartItemList.remove(cartItem);
                //更新cookie
                String objectToJson = JsonUtils.objectToJson(cartItemList);
                CookieUtils.setCookie(request,response,"TT_CART",objectToJson,true);

                return TaotaoResult.ok(cartItemList);//结束
            }

        }


        return TaotaoResult.ok(cartItemList);//结束
    }

    @Override
    public List<CartItem> getCartList(HttpServletRequest request) {

        String tt_cart = CookieUtils.getCookieValue(request, "TT_CART",true);
        List<CartItem> cartItemList = JsonUtils.jsonToList(tt_cart, CartItem.class);


        return cartItemList;
    }


}

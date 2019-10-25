package com.zy.portal.service;

import com.zy.common.pojo.TaotaoResult;
import com.zy.pojo.TbItem;
import com.zy.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService { //购物车相关

    //把商品购物车添加至cookie
    public TaotaoResult addCartItem(HttpServletRequest request, HttpServletResponse response, Long ItemId, Integer num);

    //从cookie中取出购物车
    public List<CartItem> getCartList(HttpServletRequest request);

    //更新购物车中商品的数量
    public TaotaoResult updateNum(HttpServletRequest request, HttpServletResponse response, Long ItemId, Integer num);

    //删除购物车中的商品
    public TaotaoResult delete(HttpServletRequest request, HttpServletResponse response, Long ItemId);



}

package com.zy.order.service;

import com.zy.common.pojo.TaotaoResult;
import com.zy.order.pojo.Order;
import com.zy.order.pojo.Status;

public interface OrderService {

    //创建订单
    public TaotaoResult create(Order order);

    //根据id查询订单
    public TaotaoResult getOrderById(String id);


    public TaotaoResult getOrderListByUser(Long userId, Integer page, Integer count);

    public TaotaoResult changeStatus(Status status);
}

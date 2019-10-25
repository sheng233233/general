package com.zy.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.zy.common.pojo.TaotaoResult;
import com.zy.common.utils.IDUtils;
import com.zy.mapper.TbOrderItemMapper;
import com.zy.mapper.TbOrderMapper;
import com.zy.mapper.TbOrderShippingMapper;
import com.zy.order.pojo.Order;
import com.zy.order.pojo.Status;
import com.zy.order.service.OrderService;
import com.zy.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    TbOrderMapper orderMapper;
    @Autowired
    TbOrderItemMapper orderItemMapper;
    @Autowired
    TbOrderShippingMapper orderShippingMapper;



    @Override
    public TaotaoResult create(Order order) {
        try {
            order.setOrderId(IDUtils.genOrderId());
            order.getOrderShipping().setOrderId(order.getOrderId());
            order.setPaymentType(1);
            order.setStatus(1);
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());

            //插入order表
            orderMapper.insert(order);
            //插入ordershipping表
            orderShippingMapper.insert(order.getOrderShipping());
            //插入orderItem表

            for (TbOrderItem orderItem:order.getOrderItems()) {
                orderItem.setId(IDUtils.genOrderItemId());
                orderItem.setOrderId(order.getOrderId());
                orderItemMapper.insert(orderItem);
            }

            return TaotaoResult.ok(order.getOrderId());

        }catch (Exception e){
            return TaotaoResult.build(500,"添加失败,请检查数据格式是否正确");
        }

    }

    @Override
    public TaotaoResult getOrderById(String orderId) {

        Order order = new Order();
        //查询order
        TbOrder tbOrder = orderMapper.selectByPrimaryKey(orderId);
        order.setOrderId(orderId);
        order.setPayment(tbOrder.getPayment());
        order.setCreateTime(tbOrder.getCreateTime());
        order.setPostFee(tbOrder.getPostFee());
        order.setUserId(tbOrder.getUserId());
        order.setBuyerMessage(tbOrder.getBuyerMessage());
        order.setBuyerNick(tbOrder.getBuyerNick());

        //查询orderShipping
        TbOrderShippingExample orderShippingExample = new TbOrderShippingExample();
        TbOrderShippingExample.Criteria shippingExampleCriteria = orderShippingExample.createCriteria();
        shippingExampleCriteria.andOrderIdEqualTo(orderId);
        List<TbOrderShipping> shippings = orderShippingMapper.selectByExample(orderShippingExample);
        order.setOrderShipping(shippings.get(0));

        //查询orderItem
        TbOrderItemExample orderItemExample = new TbOrderItemExample();
        TbOrderItemExample.Criteria orderItemExampleCriteria = orderItemExample.createCriteria();
        orderItemExampleCriteria.andOrderIdEqualTo(orderId);
        List<TbOrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
        order.setOrderItems(orderItems);

        return TaotaoResult.ok(order);
    }

    @Override
    public TaotaoResult getOrderListByUser(Long userId, Integer page, Integer count) {

        try {
            TbOrderExample orderExample = new TbOrderExample();
            TbOrderExample.Criteria criteria = orderExample.createCriteria();
            criteria.andUserIdEqualTo(userId);

            PageHelper.startPage(page,count);
            List<TbOrder> orderList = orderMapper.selectByExample(orderExample);


            return TaotaoResult.ok(orderList);

        }catch (Exception e){
            return TaotaoResult.build(500,"查询失败,请检查数据格式是否正确");
        }

    }

    @Override
    public TaotaoResult changeStatus(Status status) {

        /*TbOrderExample example = new TbOrderExample();
        TbOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(status.getOrderId());
        criteria.andStatusEqualTo(status.getStatus());
        criteria.andPaymentTimeEqualTo(new Date(status.getPaymentTime()));

        orderMapper.updateByExampleSelective(example);*/

        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderId(status.getOrderId());
        tbOrder.setStatus(status.getStatus());
        tbOrder.setPaymentTime(new Date(status.getPaymentTime()));

        orderMapper.updateByPrimaryKeySelective(tbOrder);


        return TaotaoResult.ok();
    }
}

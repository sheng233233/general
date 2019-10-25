package com.zy.order.controller;

import com.zy.common.pojo.TaotaoResult;
import com.zy.order.pojo.Order;
import com.zy.order.pojo.Status;
import com.zy.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TaotaoResult create(@RequestBody Order order){
        return orderService.create(order);
    }

    @ResponseBody
    @RequestMapping("/info/{orderId}")
    public TaotaoResult getInfo(@PathVariable String orderId){
        return orderService.getOrderById(orderId);
    }

    @ResponseBody
    @RequestMapping("/list/{userID}/{page}/{count}")
    public TaotaoResult getOrderListByUser(@PathVariable Long userID, @PathVariable Integer page, @PathVariable Integer count){
        return orderService.getOrderListByUser(userID, page, count);
    }

    @ResponseBody
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public TaotaoResult changeStatus(@RequestBody Status status){
        return orderService.changeStatus(status);
    }


}

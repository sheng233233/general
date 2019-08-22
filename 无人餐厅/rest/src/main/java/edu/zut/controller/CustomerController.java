package edu.zut.controller;

import edu.zut.entity.Food;
import edu.zut.entity.Order;
import edu.zut.entity.Result;
import edu.zut.service.FoodService;
import edu.zut.service.OrderService;
import edu.zut.service.TableService;
import edu.zut.service.WebSocket;
import edu.zut.utils.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    TableService ts;

    @Autowired
    FoodService fs;


    @Autowired
    private WebSocket webSocket;


    @ResponseBody
    @RequestMapping("/api/table/request")
    public Result getTableAndFoods(){
        HashMap<String, Object> hashMap = new HashMap<>();
        int tabluNum = ts.distribute();
        List<Food> foods = fs.getAll();
        if (tabluNum == 0){
            return ResultFactory.buildFailResult("暂无空位！");
        }
        if (foods == null){
            return ResultFactory.buildFailResult("暂无菜单");
        }
        hashMap.put("tableNum",tabluNum);
        hashMap.put("menu",foods);
        return ResultFactory.buildSuccessResult(hashMap);
    }


    @RequestMapping("/api/order/cart")
    public void saveOrder(Order order){
        if (order!=null){
            //发送websocket消息
            webSocket.sendMessage(order.getTid()+"号桌产生新的订单!\n"+order.getContent());
        }else {
            webSocket.sendMessage(null);
        }

    }

    @RequestMapping("/api/table/order")
    public void revoke(Integer tableNum) {
        if (tableNum != null){
            ts.revoke(tableNum);
        }
    }



}

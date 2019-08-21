package edu.zut.controller;

import edu.zut.entity.Food;
import edu.zut.entity.Order;
import edu.zut.entity.Result;
import edu.zut.service.FoodService;
import edu.zut.service.TableService;
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


    @RequestMapping("")
    public void saveOrder(Order order){




    }




}

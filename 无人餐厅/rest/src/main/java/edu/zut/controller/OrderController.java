package edu.zut.controller;

import edu.zut.entity.Order;
import edu.zut.service.OrderService;
import edu.zut.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService os;

    @RequestMapping("listOrder")
    public ModelAndView list(String pageNum){
        ModelAndView mv = new ModelAndView();
        List<Order> orders = os.getAll();
        Page page = new Page();
        int num = 1;  //若无pageNum参数,默认为1
        if (pageNum != null){
            num = Integer.parseInt(pageNum); //若有pageNum参数,将其赋值给num
        }
        page.setTotalLine(os.getNum());  //设置总条数,放在在设置当前页之前
        page.setPageNum(num);  //设置当前页

        mv.addObject("page",page);
        mv.addObject("list",orders);
        mv.setViewName("order/list");
        return mv;
    }

    @RequestMapping("find")
    public ModelAndView find(Integer status){
        ModelAndView mv = new ModelAndView();
        List<Order> orders = null;
        if (status != null && status!=-1){
            orders = os.findByStatus(status);

        }
        Page page = new Page();
        int num = 1;  //若无pageNum参数,默认为1
        page.setTotalLine(os.getNum());  //设置总条数,放在在设置当前页之前
        page.setPageNum(num);  //设置当前页

        mv.addObject("page",page);
        mv.addObject("list",orders);
        mv.setViewName("order/list");
        return mv;
    }

    @RequestMapping("details")
    public ModelAndView details(Integer id){
        ModelAndView mv = new ModelAndView();
        //查询
        Order order = os.findById(id);
        mv.addObject("order",order);
        mv.setViewName("order/detail");
        return mv;
    }

    @RequestMapping("delete")
    public ModelAndView delete(Integer id){
        ModelAndView mv = new ModelAndView();
        //删除
        os.delete(id);
        mv.addObject("order",null);
        mv.setViewName("order/detail");
        return mv;
    }

    @RequestMapping("add")
    public void add(Integer accept,String content){
        System.out.println(accept);
        String[] lines = content.split("!");
        Order order = new Order();
        String TID = lines[0].replaceAll("号桌产生新的订单","");
        order.setTid(Integer.parseInt(TID));
        order.setContent(lines[1]);
        order.setStatus(accept);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        order.setCreateTime(sdf.format(new Date()));
        System.out.println(order);
        os.add(order);
    }

}

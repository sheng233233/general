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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;


/**
 * 用于处理客户端发送的请求
 */
@Controller
public class CustomerController {

    @Autowired
    TableService ts;

    @Autowired
    FoodService fs;


    @Autowired
    private WebSocket webSocket;


    static String truePath = ""; //真实路径

    /**
     * 将数据库中存储的相对路径映射成真实路径下文件,返回byte数组
     * @param img 数据库中存储的相对路径
     * @return 真实数据
     * @throws Exception  io异常
     */
    private byte[] getDataByImg(String img) throws  Exception{
        File file = new File(truePath+img);
        FileInputStream input = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        input.read(data);
        input.close();
        return data;
    }

    /**
     * 用户起始请求,返回分配的桌号和菜单
     * @param request 用于获得项目真实路径,以便获得图像数据
     * @return 分配的桌号和菜单
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/api/table/request")
    public Result getTableAndFoods(HttpServletRequest request) throws Exception{
        truePath = request.getRealPath("image").replaceAll("image","");
        HashMap<String, Object> hashMap = new HashMap<>();
        int tabluNum = ts.distribute();
        List<Food> foods = fs.getAll();
        if (tabluNum == 0){
            return ResultFactory.buildFailResult("暂无空位！");
        }
        if (foods == null){
            return ResultFactory.buildFailResult("暂无菜单");
        }
        for (Food food:foods) {
            food.setImgData(getDataByImg(food.getImg()));
        }
        hashMap.put("tableNum",tabluNum);
        hashMap.put("menu",foods);
        return ResultFactory.buildSuccessResult(hashMap);
    }


    /**
     * 用户提交订单,处理后向后台发送消息
     * @param order
     */
    @RequestMapping("/api/order/cart")
    public void saveOrder(Order order){
        if (order!=null){
            //发送websocket消息
            webSocket.sendMessage(order.getTid()+"号桌产生新的订单!\n"+order.getContent());
        }else {
            webSocket.sendMessage(null);
        }

    }

    /**
     * 回收桌号,将桌号池中相应桌号置为可用
     * @param tableNum
     */
    @RequestMapping("/api/table/order")
    public void revoke(Integer tableNum) {
        if (tableNum != null){
            ts.revoke(tableNum);
        }
    }



}

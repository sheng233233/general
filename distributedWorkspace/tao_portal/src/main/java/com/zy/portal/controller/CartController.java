package com.zy.portal.controller;

import com.zy.common.pojo.TaotaoResult;
import com.zy.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CartController {

    @Autowired
    CartService cartService;


    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        TaotaoResult result = cartService.addCartItem(request, response, itemId, num);
        if (result.getStatus() == 200){
            return "cartSuccess";
        }
        return null;
    }

    @RequestMapping("/cart/cart")
    public String showCart(HttpServletRequest request, Model model){
        model.addAttribute("cartList"  ,cartService.getCartList(request));
        return "cart";
    }

    @ResponseBody
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    public void updateNum(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request, HttpServletResponse response){
        if (num > 0){
            cartService.updateNum(request,response,itemId,num);
        }
    }


    @RequestMapping("/cart/delete/{itemId}")
    public String delete(@PathVariable Long itemId,  HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            TaotaoResult result = cartService.delete(request, response, itemId);
            model.addAttribute("cartList",result.getData());
        }catch (Exception e){
        }
        return "cart";
    }



}

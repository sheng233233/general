package edu.zut.controller;

import edu.zut.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/table")
public class TableController {


    @Autowired
    TableService ts;

    @RequestMapping("showAll")
    public ModelAndView showAll(){
        ModelAndView mv = new ModelAndView();
        Map<Integer, Boolean> tables = ts.getAll();
        mv.addObject("tables", tables);
        mv.setViewName("table/show");
        return mv;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Object edit(Integer num){
        HashMap<String, Object> map = new HashMap<>();
        ts.setNum(num);
        map.put("success",true);
        return map;
    }



}

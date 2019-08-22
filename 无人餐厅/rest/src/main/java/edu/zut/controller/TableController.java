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

    /**
     * 后台展示桌子使用情况
     * @return
     */
    @RequestMapping("/showAll")
    public ModelAndView showAll(){
        ModelAndView mv = new ModelAndView();
        Map<Integer, Boolean> tables = ts.getAll();
        mv.addObject("tables", tables);
        mv.setViewName("table/show");
        return mv;
    }

    /**
     * 修改桌子总数
     * @param num
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public Object edit(Integer num){
        HashMap<String, Object> map = new HashMap<>();
        ts.setNum(num);
        map.put("success",true);
        return map;
    }




}

package edu.zut.controller;

import edu.zut.entity.Ingre;
import edu.zut.service.IngreService;
import edu.zut.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/ingre")
public class IngreController {

    @Autowired
    IngreService is;


    @RequestMapping("toAdd")
    public String toAdd(){
        return "ingre/add";
    }

    @RequestMapping("/add")
    public ModelAndView add(Ingre ingre){
        ModelAndView mv = new ModelAndView();
        is.add(ingre);
        mv.setViewName("redirect:/api/ingre/listIngre");
        return mv;
    }

    @RequestMapping("/listIngre")
    public ModelAndView listIngre(String pageNum){
        ModelAndView mv = new ModelAndView();
        List<Ingre> ingres = is.getAll();
        Page page = new Page();
        int num = 1;  //若无pageNum参数,默认为1
        if (pageNum != null){
            num = Integer.parseInt(pageNum); //若有pageNum参数,将其赋值给num
        }
        page.setTotalLine(is.getNum());  //设置总条数,放在在设置当前页之前
        page.setPageNum(num);  //设置当前页
        mv.addObject("page",page);
        mv.addObject("list",ingres);
        mv.setViewName("ingre/list");
        return mv;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(Integer id){
        ModelAndView mv = new ModelAndView();
        //查询
        Ingre ingre = is.findById(id);
        mv.addObject("ingre",ingre);
        mv.setViewName("ingre/edit");
        return mv;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Ingre ingre){
        ModelAndView mv = new ModelAndView();
        //修改
        is.edit(ingre);
        mv.setViewName("redirect:/api/ingre/listIngre");
        return mv;
    }

    @RequestMapping("delete")
    public ModelAndView delete(Integer id){
        ModelAndView mv = new ModelAndView();
        //删除
        is.deleteById(id);
        mv.setViewName("redirect:/api/ingre/listIngre");
        return mv;
    }
}

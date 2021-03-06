package edu.zut.controller;

import edu.zut.entity.Food;
import edu.zut.service.FoodService;
import edu.zut.utils.Page;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    FoodService fs;


    /**
     * 后台展示所有food详情
     * @param pageNum 给定当前页,可用于分页查询
     * @return
     */
    @RequestMapping("/listFood")
    public ModelAndView getAll(String pageNum){  //接收当前页
        ModelAndView mv = new ModelAndView();
        List<Food> list = fs.getAll();
        Page page = new Page();
        int num = 1;  //若无pageNum参数,默认为1
        if (pageNum != null){
            num = Integer.parseInt(pageNum); //若有pageNum参数,将其赋值给num
        }
        page.setTotalLine(fs.getNum());  //设置总条数,放在在设置当前页之前
        page.setPageNum(num);  //设置当前页

        mv.addObject("page",page);
        mv.addObject("list",list);
        mv.setViewName("food/list");
        return mv;
    }

    /**
     * 跳转向新增页面
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(){
        return "food/add";
    }

    /**
     * 添加food记录
     * @param imgFile 前端传来的多部分文件
     * @param request 获得真是路径的request
     * @param food 封装的food
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")//添加新品,将图片保存至项目真实路径,将图片名称存入数据库
    public ModelAndView add(@RequestParam MultipartFile imgFile, HttpServletRequest request,Food food) throws Exception {
        ModelAndView mv = new ModelAndView();
        String path = "image";
        String realPath = getRealPath(path, request);
        food.setImg(path+"/"+food.getName()+".png");  //向数据库存入项目相对路径

        File file = new File(realPath+File.separator+food.getName()+".png"); //保存至项目真实路径
        FileOutputStream output = new FileOutputStream(file);
        InputStream input = imgFile.getInputStream();
        IOUtils.copy(input,output);
        //关闭资源
        input.close();
        output.close();
        fs.add(food);
        mv.setViewName("redirect:/api/food/listFood");
        return mv;
    }

    /**
     * 将相对路径转化为真是路径
     * @param path  相对路径
     * @param request
     * @return 真实路径
     */
    public String getRealPath(String path, HttpServletRequest request){
        @SuppressWarnings("deprecation")
        String realPath = request.getRealPath(path);
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdir();
        }
        return realPath;
    }

    /**
     * 通过id查询后跳转到food详情页
     * @param id id
     * @return
     */
    @RequestMapping("/details")
    public ModelAndView details(Integer id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("food",fs.findById(id));
        mv.setViewName("food/detail");
        return mv;
    }

    /**
     * 通过id查询后跳转到food编辑页
     * @param id id
     * @return
     */
   @RequestMapping("/toEdit")
    public ModelAndView toEdit(Integer id){
       ModelAndView mv = new ModelAndView();
       mv.addObject("food",fs.findById(id));
       mv.setViewName("food/edit");
       return mv;
   }

    /**
     * 修改food详情
     * @param imgFile
     * @param request
     * @param food
     * @return
     * @throws Exception
     */
   @RequestMapping("edit")
    public ModelAndView edit(@RequestParam MultipartFile imgFile, HttpServletRequest request,Food food) throws Exception {
       ModelAndView mv = new ModelAndView();
       String path = "image";
       food.setImg(path+"/"+food.getName()+".png");  //向数据库存入项目相对路径
       String realPath = getRealPath(path, request);
       File file = new File(realPath+File.separator+food.getName()+".png"); //保存至项目真实路径
       FileOutputStream out = new FileOutputStream(file);
       InputStream input = imgFile.getInputStream();
       IOUtils.copy(input,out);
       //关闭资源
       out.close();
       input.close();
       fs.edit(food);
       mv.setViewName("redirect:/api/food/listFood");
       return mv;
   }

    /**
     * 删除food记录
     * @param id
     * @return
     */
   @RequestMapping("/delete")
    public ModelAndView delete(Integer id){
        ModelAndView mv = new ModelAndView();
        fs.deleteById(id);
        mv.setViewName("redirect:/api/food/listFood");
        return mv;
   }


}

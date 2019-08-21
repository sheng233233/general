package edu.zut.service;

import edu.zut.entity.Food;

import java.util.List;

public interface FoodService {

    //根据id刪除记录
    public boolean deleteById(Integer id);

    //增加一条记录
    public boolean add(Food food);

    //查询所有记录
    public List<Food> getAll();

    //更新记录
    public boolean edit(Food food);


    public int getNum();

    public Food findById(Integer id);
}

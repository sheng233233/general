package edu.zut.mapper;

import edu.zut.entity.Food;

import java.util.List;

public interface FoodMapper {

    public int insert(Food food);

    public List<Food> selectAll();

    public int getNum();

    public int deleteById(Integer id);

    public int updateByid(Food food);

    public Food findById(Integer id);

    public void delete(Integer id);
}
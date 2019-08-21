package edu.zut.service.impl;

import edu.zut.entity.Food;
import edu.zut.mapper.FoodMapper;
import edu.zut.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodMapper foodMapper;

    @Override
    public boolean deleteById(Integer id) {
        return foodMapper.deleteById(id) == 1;
    }

    @Override
    public boolean add(Food food) {
        return foodMapper.insert(food) == 1;
    }

    @Override
    public List<Food> getAll() {
        return foodMapper.selectAll();
    }

    @Override
    public boolean edit(Food food) {
        return foodMapper.updateByid(food) == 1;
    }

    @Override
    public int getNum() {
        return foodMapper.getNum();
    }

    @Override
    public Food findById(Integer id) {
        return foodMapper.findById(id);
    }


}

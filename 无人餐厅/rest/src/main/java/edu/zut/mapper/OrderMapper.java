package edu.zut.mapper;

import edu.zut.entity.Order;

import java.util.List;

public interface OrderMapper {



    public List<Order> getAll();

    public Order findById(Integer id);

    public List<Order> findByStatus(Integer status);

    public void delete(Integer id);

    public int getNum();

    public void insert(Order order);
}
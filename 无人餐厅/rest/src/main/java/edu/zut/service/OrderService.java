package edu.zut.service;

import edu.zut.entity.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getAll();

    public void delete(Integer id);

    public Order findById(Integer id);

    public List<Order> findByStatus(Integer status);

    public int getNum();

    public void add(Order order);
}

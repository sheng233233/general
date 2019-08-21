package edu.zut.service.impl;

import edu.zut.entity.Order;
import edu.zut.mapper.OrderMapper;
import edu.zut.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper om;

    @Override
    public List<Order> getAll() {
        return om.getAll();
    }

    @Override
    public void delete(Integer id) {
        om.delete(id);
    }

    @Override
    public Order findById(Integer id) {
        return om.findById(id);
    }

    @Override
    public List<Order> findByStatus(Integer status) {
        return om.findByStatus(status);
    }

    @Override
    public int getNum() {
        return om.getNum();
    }

    @Override
    public void add(Order order) {
        om.insert(order);
    }
}

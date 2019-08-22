package edu.zut.service;

import edu.zut.entity.Order;

import java.util.List;

public interface OrderService {

    /**
     * 获得所有订单详情
     * @return
     */
    public List<Order> getAll();

    /**
     * 根据id删除订单记录
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据id查询记录,用于详情展示
     * @param id
     * @return
     */
    public Order findById(Integer id);

    /**
     * 根据订单状态查询记录
     * @param status 状态
     * @return
     */
    public List<Order> findByStatus(Integer status);

    /**
     * 获得记录条数
     * @return
     */
    public int getNum();

    /**
     * 增加记录
     * @param order 封装的order对象
     */
    public void add(Order order);
}

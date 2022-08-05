package com.sg.service;

import com.sg.entity.Orders;
import com.sg.entity.UserAddress;
import com.sg.vo.OrderVo;

import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 10:16
 */
public interface OrderService {

    void createOrder(Orders order);

    List<OrderVo> selectPaidListByStatus(int status, int userId);

    List<OrderVo> selectPaidListByStatus(int userId);

    List<UserAddress> selectAddress(int userId);

    int orderPay(Orders orders);

    void orderFinish(int orderId);

    int orderDelete(int orderId);

    void orderDispatch(int orderId);

    void orderDeliver(int orderId);

    Orders selectOrderById(int lastUserId, int id);
}

package com.sg.service;

import com.sg.entity.OrderRefunds;
import com.sg.entity.User;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 16:11
 */
public interface OrderRefundService {
    void orderRefundApply(OrderRefunds orderRefunds);

    void orderRefundProcess(User user, OrderRefunds orderRefunds);
}

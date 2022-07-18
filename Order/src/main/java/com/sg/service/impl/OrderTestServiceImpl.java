package com.sg.service.impl;

import com.sg.dao.OrdersDao;
import com.sg.entity.Orders;
import org.springframework.stereotype.Service;
import com.sg.service.OrderTestService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 18:42
 */
@Service
public class OrderTestServiceImpl implements OrderTestService {

    @Resource
    private OrdersDao ordersDao;


    @Override
    public List<Orders> test(int id) {
        System.out.println(id);
        return ordersDao.selectList(null);
    }
}

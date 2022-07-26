package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sg.dao.OrderRefundsDao;
import com.sg.dao.OrdersDao;
import com.sg.entity.OrderRefunds;
import com.sg.entity.Orders;
import com.sg.service.OrderRefundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 16:11
 */
@Service
public class OrderRefundServiceImpl implements OrderRefundService {

    @Resource
    private OrderRefundsDao orderRefundsDao;

    @Resource
    private OrdersDao ordersDao;

    @Override
    public void orderRefundApply(OrderRefunds orderRefunds) {
        UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",orderRefunds.getOrderId())
                        .set("order_status",4);
        ordersDao.update(null,wrapper);
        orderRefunds.setBackMoney((int)orderRefunds.getBackMoney());
        orderRefunds.setCreateTime(new Date());
        orderRefundsDao.insert(orderRefunds);
    }
}

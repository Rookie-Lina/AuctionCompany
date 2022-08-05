package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sg.dao.OrderRefundsDao;
import com.sg.dao.OrdersDao;
import com.sg.dao.UserDao;
import com.sg.dao.UserScoreDao;
import com.sg.entity.OrderRefunds;
import com.sg.entity.Orders;
import com.sg.entity.User;
import com.sg.entity.UserScore;
import com.sg.service.OrderRefundService;
import com.sg.service.UserScoreService;
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

    @Resource
    private UserScoreDao userScoreDao;

    @Resource
    private UserDao userDao;

    @Override
    public void orderRefundApply(OrderRefunds orderRefunds) {
        UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", orderRefunds.getOrderId())
                .set("order_status", 4);
        ordersDao.update(null, wrapper);
        orderRefunds.setBackMoney((int) orderRefunds.getBackMoney());
        orderRefunds.setCreateTime(new Date());
        orderRefundsDao.insert(orderRefunds);
    }

    @Override
    public void orderRefundProcess(User user, OrderRefunds orderRefunds) {
        orderRefunds.setRefundTime(new Date());
        // 同意退款
        if (orderRefunds.getRefundStatus() == 1) {
            int score = (int)orderRefunds.getBackMoney();
            orderRefunds.setBackScore(score);
            // 创建积分返还
            Orders orders = ordersDao.selectById(orderRefunds.getOrderId());
            UserScore userScore = new UserScore();
            userScore.setScore(score);
            userScore.setScoreType(2);
            userScore.setCreateTime(new Date());
            userScore.setDataId(orderRefunds.getOrderId());
            userScore.setDataSrc(3);
            userScore.setUserId(orders.getUserId());
            userScoreDao.insert(userScore);
            // 减少用户积分
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.eq("id",orders.getUserId())
                    .setSql(" user_score = user_score - "+ score);
            userDao.update(null,wrapper);
        }
        // 拒绝退款
        else {
            // 空
        }
        orderRefunds.setRefundRemark(user.getLoginName()+"-"+orderRefunds.getRefundRemark());
        UpdateWrapper<OrderRefunds> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",orderRefunds.getId());
        orderRefundsDao.update(orderRefunds,wrapper);
    }
}

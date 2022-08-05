package com.sg.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sg.dao.OrderRefundsDao;
import com.sg.dao.OrdersDao;
import com.sg.dao.UserAddressDao;
import com.sg.entity.OrderRefunds;
import com.sg.entity.Orders;
import com.sg.entity.UserAddress;
import com.sg.service.OrderService;
import com.sg.vo.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 10:16
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrdersDao ordersDao;

    @Resource
    private UserAddressDao userAddressDao;

    @Resource
    private OrderRefundsDao orderRefundsDao;

    // 创建订单
    @Override
    public void createOrder(Orders order) {
        order.setOrderNo(IdUtil.getSnowflakeNextIdStr());
        order.setOrderStatus(-1);
        order.setIsPay(0);
        order.setCreateTime(new Date());
        ordersDao.insert(order);
    }

    @Override
    public List<OrderVo> selectPaidListByStatus(int status,int userId) {
        return ordersDao.selectOrderByStatus(status,userId);
    }

    @Override
    public List<OrderVo> selectPaidListByStatus(int userId) {
        return ordersDao.selectOrderById(userId);
    }

    @Override
    public List<UserAddress> selectAddress(int userId) {
        QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return userAddressDao.selectList(wrapper);
    }

    @Override
    public int orderPay(Orders orders) {
        UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", orders.getId());
        wrapper.ne("is_pay", 1);
        return ordersDao.update(orders, wrapper);
    }

    @Override
    public void orderFinish(int orderId) {
        UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", orderId)
                .set("order_status", 3);
        UpdateWrapper<OrderRefunds> wrapper1 = new UpdateWrapper<>();
        wrapper1.eq("order_id", orderId)
                .set("refund_status", -1);
        ordersDao.update(null, wrapper);
        orderRefundsDao.update(null,wrapper1);
    }

    @Override
    public int orderDelete(int orderId) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("id", orderId)
                .eq("order_status", 3);
        return ordersDao.delete(wrapper);
    }

    @Override
    public void orderDispatch(int orderId) {
        UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",orderId)
                .set("order_status",1);
        ordersDao.update(null,wrapper);
    }

    @Override
    public void orderDeliver(int orderId) {
        UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",orderId)
                .set("order_status",2);
        ordersDao.update(null,wrapper);
    }

    @Override
    public Orders selectOrderById(int lastUserId, int id) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",lastUserId)
                .eq("goods_id",id);
        return ordersDao.selectOne(wrapper);
    }

}

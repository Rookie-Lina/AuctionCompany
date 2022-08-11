package com.sg.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.dao.GoodsDao;
import com.sg.dao.OrderRefundsDao;
import com.sg.dao.OrdersDao;
import com.sg.dao.UserAddressDao;
import com.sg.entity.Goods;
import com.sg.entity.OrderRefunds;
import com.sg.entity.Orders;
import com.sg.entity.UserAddress;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.OrderService;
import com.sg.vo.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


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

    @Resource
    private GoodsDao goodsDao;

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
                .set("order_status",3);
        ordersDao.update(null,wrapper);
    }

    @Override
    public Orders selectOrderById(int lastUserId, int id) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",lastUserId)
                .eq("goods_id",id);
        return ordersDao.selectOne(wrapper);
    }

    @Override
    public Result listOrder(int current, int pageSize,int type) {
        IPage<Orders> iPage=new Page<>(current,pageSize);
        //查询到订单信息
        LambdaQueryWrapper<Orders> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Orders::getOrderStatus,type);
        IPage<Orders> iPage1 = ordersDao.selectPage(iPage, lambdaQueryWrapper);
        List<Orders> records = iPage1.getRecords();
        List<OrderVo> orderVos=new ArrayList<>();
        for(Orders o:records){
            //替换vo对象
            OrderVo orderVo=new OrderVo();
            orderVo.setId(o.getId());
            orderVo.setOrderNo(o.getOrderNo());
            orderVo.setUserId(o.getUserId());
            orderVo.setGoodsId(o.getGoodsId());
            orderVo.setAddressId(o.getAddressId());
            orderVo.setDeliverType(o.getDeliverType());
            orderVo.setOrderStatus(o.getOrderStatus());
            orderVo.setPayFrom(o.getPayFrom());
            orderVo.setIsPay(o.getIsPay());
            orderVo.setOrderRemarks(o.getOrderRemarks());
            orderVo.setCreateTime(o.getCreateTime());
            //查询商品的成交价和商品名称
            LambdaQueryWrapper<Goods> lambdaQueryWrapper1=new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(Goods::getId,o.getGoodsId());
            Goods goods = goodsDao.selectOne(lambdaQueryWrapper1);
            orderVo.setGoodsName(goods.getGoodsName());
            orderVo.setGoodsPrice(goods.getNowPrice());
            orderVos.add(orderVo);
        }
        Map<String,Object> result=new HashMap<>();
        result.put("current",current);
        result.put("pageSize",pageSize);
        result.put("total",iPage1.getTotal());
        result.put("datas",orderVos);
        return new SuccessResult(200,"查询订单列表成功",result);
    }
    @Override
    public Result deliver(Integer id) {
        ordersDao.deliver(id);
        return new SuccessResult(200,"发货成功");

    }

}

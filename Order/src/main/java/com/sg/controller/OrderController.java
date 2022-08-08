package com.sg.controller;

import com.sg.entity.Orders;
import com.sg.entity.UserAddress;
import com.sg.result.Result;
import com.sg.result.impl.ErrorResult;
import com.sg.result.impl.SuccessResult;
import com.sg.service.OrderService;
import com.sg.service.UserAddressService;
import com.sg.service.UserScoreService;
import com.sg.vo.OrderVo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 11:37
 */
@RestController
@RequestMapping("/order")
@Transactional
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private UserScoreService userScoreService;

    @Resource
    private UserAddressService userAddressService;

    // 根据id查询地址信息
    @GetMapping("/addrOne")
    public Result userAddressById(int id) {
        UserAddress userAddress = userAddressService.selectAddressById(id);
        return new SuccessResult(userAddress);
    }

    // 根据订单状态 查询订单
    @GetMapping("/list/{status}")
    public Result orderList(@PathVariable int status, int userId) {
        List<OrderVo> orderVos = orderService.selectPaidListByStatus(status, userId);
        return new SuccessResult(orderVos);
    }

    // 根据已支付查询订单
    @GetMapping("/list")
    public Result orderList(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        List<OrderVo> orderVos = orderService.selectPaidListByStatus(userId);
        return new SuccessResult(orderVos);
    }

    // 创建订单
    @PostMapping("/create")
    public Result createOrder(int lastUserId, int id) {
        if (lastUserId != 0) {
            // 防止重复提交
            Orders order = orderService.selectOrderById(lastUserId, id);
            if (order != null)
                return new ErrorResult("请不要重复提交");
            order = new Orders();
            order.setUserId(lastUserId);
            order.setGoodsId(id);
            orderService.createOrder(order);
        }
        return new SuccessResult();
    }

    // 支付订单
    @PostMapping("/pay")
    public Result orderPay(@RequestBody Orders orders, double price) {
        orders.setIsPay(1);
        orders.setOrderStatus(0);
        int i = orderService.orderPay(orders);
        if (i <= 0) return new ErrorResult("订单已支付");
        userScoreService.orderApplyScore(orders.getId(), orders.getUserId(), price);
        return new SuccessResult();
    }

    // 完成订单
    @PostMapping("/finish")
    public Result orderFinish(int orderId) {
        orderService.orderFinish(orderId);
        return new SuccessResult("订单完成");
    }

    // 删除订单
    @DeleteMapping("/{orderId}")
    public Result orderDelete(@PathVariable int orderId) {
        if (orderService.orderDelete(orderId) <= 0)
            return new ErrorResult("未完成订单无法删除");
        return new SuccessResult("订单删除成功");
    }

    // 用户地址查询
    @GetMapping("/address")
    public Result queryAddress(int userId) {
        List<UserAddress> list = orderService.selectAddress(userId);
        return new SuccessResult(list);
    }

    // 订单发货
    @PostMapping("/dispatch")
    public Result orderDispatch(int orderId) {
        orderService.orderDispatch(orderId);
        return new SuccessResult("发货成功");
    }

    // 确认送达
    @PostMapping("/deliver")
    public Result orderDeliver(int orderId) {
        orderService.orderDeliver(orderId);
        return new SuccessResult("已确认送达");
    }
}

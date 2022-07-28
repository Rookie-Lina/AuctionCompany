package com.sg.controller;

import com.sg.bo.UserOrderRefundBo;
import com.sg.entity.OrderRefunds;
import com.sg.entity.User;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.OrderRefundService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 16:11
 */
@RestController
@RequestMapping("/refund")
public class OrderRefundsController {

    @Resource
    private OrderRefundService orderRefundService;

    // 订单退货申请
    @PostMapping("/apply")
    public Result orderReturn(@RequestBody OrderRefunds orderRefunds) {
        orderRefundService.orderRefundApply(orderRefunds);
        return new SuccessResult();
    }

    // 管理员处理退款订单
    @PostMapping("/process")
    public Result orderRefundProcess(@RequestBody UserOrderRefundBo userOrderRefundBo){
        orderRefundService.orderRefundProcess(userOrderRefundBo.getUser(),userOrderRefundBo.getOrderRefunds());
        return new SuccessResult("退款成功");
    }
}

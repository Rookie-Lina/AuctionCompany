package com.sg.controller;


import cn.hutool.core.util.IdUtil;
import com.sg.dao.OrderRefundsDao;
import com.sg.entity.Orders;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.OrderTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 18:36
 */
@RestController
public class TestController {

    @Resource
    private OrderTestService orderTestService;

    @Resource
    private OrderRefundsDao orderRefundsDao;

    @GetMapping("/test")
    public Result test(@RequestParam int id){
        List<Orders> test = orderTestService.test(id);
        System.out.println(orderRefundsDao.selectList(null));
        System.out.println("test");
        return new SuccessResult(200,test.toString());
    }

    @GetMapping("/aaa")
    public String aaa(){
        System.out.println(IdUtil.getSnowflakeNextIdStr());
        return "aaa";
    }
}

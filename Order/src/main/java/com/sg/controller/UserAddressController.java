package com.sg.controller;

import com.sg.entity.UserAddress;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.UserAddressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-15 16:56
 */
@RestController
@RequestMapping("/address")
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;

    @PostMapping("/add")
    public Result addAddress(@RequestBody UserAddress userAddress) {
        userAddressService.addAddress(userAddress);
        return new SuccessResult();
    }
}

package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sg.dao.UserAddressDao;
import com.sg.entity.UserAddress;
import com.sg.service.UserAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-03 11:28
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    private UserAddressDao userAddressDao;

    @Override
    public UserAddress selectAddressById(int id) {
        QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return userAddressDao.selectOne(wrapper);
    }

    @Override
    public void addAddress(UserAddress userAddress) {
        userAddressDao.insert(userAddress);
    }
}

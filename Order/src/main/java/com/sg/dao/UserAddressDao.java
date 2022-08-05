package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.UserAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 11:05
 */
@Mapper
public interface UserAddressDao extends BaseMapper<UserAddress> {
}

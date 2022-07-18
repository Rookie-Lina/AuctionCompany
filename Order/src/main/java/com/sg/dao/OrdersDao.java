package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 18:40
 */
@Mapper
public interface OrdersDao extends BaseMapper<Orders> {
}

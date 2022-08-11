package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.vo.OrderVo;
import com.sg.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 18:40
 */
@Mapper
public interface OrdersDao extends BaseMapper<Orders> {

    List<OrderVo> selectOrderByStatus(@Param("status") int status,@Param("userId") int userId);

    List<OrderVo> selectOrderById(int userId);

    void deliver(Integer id);
}

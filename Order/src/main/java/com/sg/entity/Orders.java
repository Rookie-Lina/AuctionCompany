package com.sg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Description 订单列表
 * @auther Rookie_lin
 * @create 2022-07-18 14:36
 */
@Data
@TableName("orders")
public class Orders {

    private int id; // 自增ID
    private String orderNo; // 订单号
    private int userId; // 用户ID
    private int goodsId; // 商品ID
    private int addressId; // 收货地址ID
    private int deliverType; // 收货方式
    private int orderStatus; // 订单状态
    private int payFrom; // 支付来源
    private int isPay; // 是否支付
    private String orderRemarks; // 订单备注
    private Date createTime; // 下单时间

}

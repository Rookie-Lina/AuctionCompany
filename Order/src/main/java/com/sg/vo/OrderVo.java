package com.sg.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 11:45
 */
@Data
public class OrderVo {

    private int id; // 自增ID
    private String orderNo; // 订单号
    private int userId; // 用户ID
    private int goodsId; // 商品ID
    private String goodsName; // 商品名称
    private double goodsPrice; // 商品价钱
    private int addressId; // 收货地址ID
    private int deliverType; // 收货方式
    private int orderStatus; // 订单状态
    private int payFrom; // 支付来源
    private int isPay; // 是否支付
    private String orderRemarks; // 订单备注
    private Date createTime; // 下单时间
}

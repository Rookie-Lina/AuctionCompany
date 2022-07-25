package com.sg.entity;


import lombok.Data;

import java.util.Date;

/**
 * @Description 订单退款
 * @auther Rookie_lin
 * @create 2022-07-18 15:10
 */
@Data
public class OrderRefunds {

    private int id; // 主键id
    private int orderId; // 订单id
    private double backMoney; // 用户退款金额
    private String refundReason; // 用户申请退款原因
    private String refundRemark; // 管理员退款备注
    private Date refundTime; // 管理员退款时间
    private String  shopRejectReason; // 店铺不同意拒收原因
    private int refundStatus; // 退款状态
    private Date createTime; // 用户申请退款时间
    private int backScore; // 待返回积分

}

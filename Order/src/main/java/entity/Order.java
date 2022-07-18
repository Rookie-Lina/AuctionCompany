package entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 14:36
 */
@Data
public class Order {

    private int id; // 自增ID
    private String orderNo; // 订单号
    private int userId; // 用户ID
    private double goodsMoney; // 商品总金额
    private int deliverType; // 收货方式
    private double realTotalMoney; // 实际订单总金额
    private int orderStatus; // 订单状态
    private int payForm; // 支付来源
    private int isPay; // 是否支付
    private String userName; // 收货人名称
    private String userAddress; // 收件人地址
    private String userPhone; // 收件人手机号码
    private String orderRemarks; // 订单备注
    private Date createTime; // 下单时间

}

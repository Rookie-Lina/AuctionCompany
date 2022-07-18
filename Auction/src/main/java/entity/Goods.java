package entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description 商品列表
 * @auther Rookie_lin
 * @create 2022-07-18 16:39
 */

@Data
public class Goods {

    private int id; // 自增ID
    private int userId; // 拍卖者ID
    private String goodsName; // 商品名称
    private String goodImg; // 商品图片
    private String goodInfo; // 商品描述
    private double marketPrice; // 预估市场价
    private double startingPrice; // 起拍价
    private double minIncr; // 最低加价
    private int goodTypeId; // 商品类别的ID
    private double nowPrice; // 商品的当前售价
    private int lastUserId; // 当前竞拍到最高拍卖价的用户ID
    private Date raiseTime; // 最后一个买家的最高拍卖价的更新时间
}

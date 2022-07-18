package entity;

import java.util.Date;

/**
 * @Description 商品列表
 * @auther Rookie_lin
 * @create 2022-07-18 16:39
 */
public class Goods {

    private int id;
    private int userId;
    private String goodsName;
    private String goodImg;
    private String goodInfo;
    private double marketPrice;
    private double startingPrice;
    private double minIncr;
    private int goodTypeId;
    private double nowPrice;
    private int lastUserId;
    private Date raiseTime;
}

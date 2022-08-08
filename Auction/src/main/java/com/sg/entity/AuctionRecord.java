package com.sg.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-08 10:09
 */
@Data
public class AuctionRecord {

    private int id;
    private int goodsId;
    private int userId;
    private double nowPrice;
    private int finish;
    private Date createTime;
}

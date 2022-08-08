package com.sg.service;


import com.sg.entity.AuctionRecord;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-08 10:10
 */

public interface AuctionRecordService {

    int auction(AuctionRecord auctionRecord);

    int finishAuction(int goodsId);

    AuctionRecord newAuction(int goodsId);
}

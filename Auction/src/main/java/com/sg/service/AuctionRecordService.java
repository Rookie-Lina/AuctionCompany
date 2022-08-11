package com.sg.service;


import com.sg.entity.AuctionRecord;

import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-08 10:10
 */

public interface AuctionRecordService {

    int auction(AuctionRecord auctionRecord);

    int finishAuction(int goodsId);

    AuctionRecord newAuction(int goodsId);

    List<AuctionRecord> selectAuctionRecordByUserId(int userId);
}

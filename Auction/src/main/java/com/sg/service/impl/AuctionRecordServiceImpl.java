package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sg.dao.AuctionRecordDao;
import com.sg.dao.GoodsDao;
import com.sg.entity.AuctionRecord;
import com.sg.entity.Goods;
import com.sg.service.AuctionRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-08 10:11
 */
@Service
public class AuctionRecordServiceImpl implements AuctionRecordService {

    @Resource
    private AuctionRecordDao auctionRecordDao;

    @Resource
    private GoodsDao goodsDao;

    // 竞拍商品
    public int auction(AuctionRecord auctionRecord) {
        auctionRecord.setCreateTime(new Date());
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",auctionRecord.getGoodsId())
                .set("last_user_id",auctionRecord.getUserId())
                .set("now_price",auctionRecord.getNowPrice())
                .set("raise_time",auctionRecord.getCreateTime());
        goodsDao.update(null,wrapper);
        return auctionRecordDao.insert(auctionRecord);
    }

    @Override
    public int finishAuction(int goodsId) {
        AuctionRecord auctionRecord = newAuction(goodsId);
        UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", goodsId).set("raise_time", new Date());
        if (auctionRecord == null)
            updateWrapper.set("finish", -1);
        else
            updateWrapper.set("last_user_id", auctionRecord.getUserId())
                    .set("now_price", auctionRecord.getNowPrice());

        return goodsDao.update(null, updateWrapper);
    }

    @Override
    public AuctionRecord newAuction(int goodsId) {
        QueryWrapper<AuctionRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id", goodsId)
                .orderByDesc("create_time");
        return auctionRecordDao.selectOne(wrapper);
    }
}

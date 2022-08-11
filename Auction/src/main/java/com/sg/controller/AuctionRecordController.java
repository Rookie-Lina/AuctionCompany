package com.sg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.entity.AuctionRecord;
import com.sg.entity.Goods;
import com.sg.result.Result;
import com.sg.result.impl.ErrorResult;
import com.sg.result.impl.SuccessResult;
import com.sg.service.AuctionRecordService;
import com.sg.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-08 10:16
 */

@RestController
@RequestMapping("/auction")
public class AuctionRecordController {

    @Resource
    private AuctionRecordService auctionRecordService;

    @Resource
    private GoodsService goodsService;

    // 用户出价竞拍
    @PostMapping("/bid")
    public Result auction(@RequestBody AuctionRecord auctionRecord) {
        //
        if (auctionRecordService.auction(auctionRecord) <= 0)
            return new ErrorResult("出价错误");
        else return new SuccessResult("出价成功");
    }

    // 完成订单
    @PostMapping("/finish")
    public Result finishAuction(int goodsId) {
        if (auctionRecordService.finishAuction(goodsId) <= 0)
            return new ErrorResult("错误");
        else {
            return new SuccessResult("成功");
        }
    }

    // 查询最新出价信息
    @GetMapping("/new")
    public Result newAuction(int goodsId) {
        AuctionRecord auctionRecord = auctionRecordService.newAuction(goodsId);
        return new SuccessResult(auctionRecord);
    }

    // 根据拍卖者Id查询竞拍记录
    @GetMapping("/record")
    public Result auctionRecordByUserId(int current, int userId) {
        List<AuctionRecord> auctionRecords = auctionRecordService.selectAuctionRecordByUserId(userId);
        List<Integer> list = new ArrayList<>();
        auctionRecords.forEach(i -> list.add(i.getGoodsId()));
        Page<Goods> goodsPage = new Page<>(current,4);
        goodsService.selectGoodByListGoodsId(list,goodsPage);
        return new SuccessResult(goodsPage);
    }
}

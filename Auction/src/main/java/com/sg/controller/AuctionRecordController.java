package com.sg.controller;

import com.sg.entity.AuctionRecord;
import com.sg.entity.Goods;
import com.sg.result.Result;
import com.sg.result.impl.ErrorResult;
import com.sg.result.impl.SuccessResult;
import com.sg.service.AuctionRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public Result newAuction(int goodsId){
        AuctionRecord auctionRecord = auctionRecordService.newAuction(goodsId);
        return new SuccessResult(auctionRecord);
    }
}

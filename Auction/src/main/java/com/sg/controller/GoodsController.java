package com.sg.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sg.entity.Goods;
import com.sg.result.Result;
import com.sg.result.impl.ErrorResult;
import com.sg.result.impl.SuccessResult;
import com.sg.service.GoodsService;
import com.sg.vo.GoodsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-19 11:00
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    // 查询 所有商品总数
    @GetMapping("/count")
    public Result queryGoodsCountByType(@RequestParam List<Integer> goodsType) {
        System.out.println(goodsType);
        int i = goodsService.goodsCount(goodsType);
        return new SuccessResult(i);
    }

    //查询 分页分类查询商品
    @GetMapping("/list")
    public Result queryGoodsList(int current, @RequestParam List<Integer> goodsType) {
        IPage<Goods> goodsIPage = goodsService.selectGoodsList(current, 4, goodsType);
        return new SuccessResult(200, "查询成功", goodsIPage);
    }

    //根据ID查询商品信息查询商品信息
    @GetMapping("/id")
    public Result queryGoodById(int id) {
        Goods goods = goodsService.selectGoodById(id);
        GoodsVo goodsVo = new GoodsVo();
        BeanUtils.copyProperties(goods,goodsVo);
        if (goods.getRaiseTime()!=null)
            goodsVo.setRaiseTime(goods.getRaiseTime().getTime());
        return new SuccessResult(goodsVo);
    }

    // 搜索商品
    @GetMapping("/search")
    public Result searchGoods( int current, String search) {
        IPage<Goods> goodsIPage = goodsService.selectGoodsListByName( current, 4, search);
        return new SuccessResult(goodsIPage);
    }

    // 用户出价竞拍
    @PostMapping("/auction")
    public Result auction(@RequestBody Goods goods) {
        // TODO 将竞拍商品存入redis中加快响应速度
        /**
         *  key : goods-auction+id
         *  value: hash :
         *          nowPrice,lastUserId,raiseTime
         */
        if (goods.getLastUserId() == goods.getUserId() || goodsService.auction(goods) <= 0)
            return new ErrorResult("出价错误");
        else return new SuccessResult("出价成功");
    }

    @PostMapping("/finish")
    public Result finishAuction(@RequestBody Goods goods) {
        if (goodsService.finishAuction(goods) <= 0)
            return new ErrorResult("错误");
        else return new SuccessResult("成功");
    }
}

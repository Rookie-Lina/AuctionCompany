package com.sg.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sg.entity.Goods;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public Result queryGoodsCountByType(@RequestParam int goodsType) {
        int i = goodsService.goodsCount(goodsType);
        return new SuccessResult(200, String.valueOf(i));
    }

    //查询 分页分类查询商品
    @GetMapping("/list")
    public Result queryGoodsList(int count, int current, int goodsType) {
        int size = count % 10 == 0 ? count / 10 : count / 10 + 1;
        IPage<Goods> goodsIPage = goodsService.selectGoodsList(current, size, goodsType);
        return new SuccessResult(200, "查询成功", goodsIPage);
    }

    //根据ID查询商品信息查询商品信息
    @GetMapping("/{id}")
    public Result queryGoodById(@PathVariable int id) {
        Goods goods = goodsService.selectGoodById(id);
        return new SuccessResult(goods);
    }

}

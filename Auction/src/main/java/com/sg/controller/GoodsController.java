package com.sg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.dao.GoodsDao;
import com.sg.entity.Goods;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/count")
    public Result queryGoodsCountByType(@RequestParam int goodsType) {
        int i = goodsService.goodsCount(goodsType);
        return new SuccessResult(200, String.valueOf(i));
    }

    @GetMapping("/list")
    public Result queryGoodsList(int count, int current,int goodsType) {
        int size = count % 10 == 0 ? count / 10 : count / 10 + 1;
        IPage<Goods> goodsIPage = goodsService.selectGoodsList(current, size, goodsType);
        return new SuccessResult(200,"查询成功",goodsIPage);
    }

}

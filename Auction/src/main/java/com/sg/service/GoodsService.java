package com.sg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sg.entity.Goods;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 20:29
 */
public interface GoodsService {

    Object test();

    int goodsCount(int goodsType);

    IPage<Goods> selectGoodsList(int current, int size, int goodsType);

    Goods selectGoodById(int id);
}

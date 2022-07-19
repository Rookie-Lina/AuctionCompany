package com.sg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sg.entity.Goods;

import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 20:29
 */
public interface GoodsService {

    Object test();

    int goodsCount(List<Integer> goodsType);

    IPage<Goods> selectGoodsList(int count,int current, int size, List<Integer> goodsType);

    Goods selectGoodById(int id);

    int auction(Goods goods);

    int finishAuction(Goods goods);

    int goodsCountLikeName(String search);

    IPage<Goods> selectGoodsListByName(int count,int current, int size, String search);
}

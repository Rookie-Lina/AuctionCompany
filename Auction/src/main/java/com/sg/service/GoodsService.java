package com.sg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.entity.Goods;

import java.util.List;


public interface GoodsService {

    Object test();

    int goodsCount(List<Integer> goodsType);

    IPage<Goods> selectGoodsListNo(int current, int size);

    Goods selectGoodById(int id);

    int auction(Goods goods);

    int finishAuction(Goods goods);

    int goodsCountLikeName(String search);

    IPage<Goods> selectGoodsListByName(int current, int size, String search);

    IPage<Goods> selectGoodsListByType(int current, int size, String goodsType);

    void selectGoodByUserId(int userId, Page<Goods> page,int finish);
}

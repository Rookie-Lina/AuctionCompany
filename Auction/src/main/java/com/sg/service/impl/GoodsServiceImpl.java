package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.dao.GoodsDao;
import com.sg.entity.Goods;
import com.sg.result.Result;
import com.sg.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 20:29
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    public Object test() {
        return goodsDao.selectList(null);
    }

    // 查询 所有商品总数
    public int goodsCount(int goodsType) {
        return goodsDao.selectGoodsCount(goodsType);
    }

    //查询 分页分类查询商品
    public IPage<Goods> selectGoodsList(int current, int size, int goodsType) {
        Page<Goods> page = new Page<>(current, size);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("good_type_id", goodsType);
        IPage<Goods> goodsIPage = goodsDao.selectPage(page, wrapper);
        return goodsIPage;
    }

    public Goods selectGoodById(int id){ return goodsDao.selectById(id);}


}

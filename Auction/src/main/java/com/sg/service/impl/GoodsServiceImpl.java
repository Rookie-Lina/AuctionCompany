package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import java.util.Date;
import java.util.List;

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
    public int goodsCount(List<Integer> goodsType) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        return goodsDao.selectGoodsCount(goodsType);
    }

    //查询 分页分类查询商品
    public IPage<Goods> selectGoodsList(int current, int size, List<Integer> goodsType) {
        Page<Goods> page = new Page<>(current, size);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.in("good_type_id", goodsType);
        wrapper.eq("finish",0);
        IPage<Goods> goodsIPage = goodsDao.selectPage(page, wrapper);
        return goodsIPage;
    }
    //根据ID查询商品信息查询商品信息
    public Goods selectGoodById(int id){ return goodsDao.selectById(id);}


    // 竞拍商品
    public int auction(Goods goods) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",goods.getId())
                .set("now_price",goods.getNowPrice())
                .set("last_user_id",goods.getLastUserId())
                .set("raise_time", new Date());
        return goodsDao.update(null, wrapper);
    }

    @Override
    public int finishAuction(Goods goods) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",goods.getId())
                .eq("last_user_id",goods.getLastUserId())
                .set("finish",goods.getFinish());
        return goodsDao.update(null,wrapper);
    }


}

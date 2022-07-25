package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.dao.GoodsDao;
import com.sg.entity.Goods;
import com.sg.service.GoodsService;
import org.springframework.stereotype.Service;

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
        wrapper.in("good_type_id", goodsType);
        return goodsDao.selectCount(wrapper);
    }

    //查询 分页分类查询商品
    public IPage<Goods> selectGoodsList(int current, int size, List<Integer> goodsType) {
        Page<Goods> page = new Page<>(current, size);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.in("good_type_id", goodsType);
        wrapper.eq("finish", 0);
        IPage<Goods> goodsIPage = goodsDao.selectPage(page, wrapper);
        return goodsIPage;
    }

    //根据ID查询商品信息查询商品信息
    public Goods selectGoodById(int id) {
        return goodsDao.selectById(id);
    }


    // 竞拍商品
    public int auction(Goods goods) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", goods.getId())
                .set("now_price", goods.getNowPrice())
                .set("last_user_id", goods.getLastUserId())
                .set("raise_time", new Date());
        return goodsDao.update(null, wrapper);
    }

    @Override
    public int finishAuction(Goods goods) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", goods.getId());
        if (goods.getLastUserId() != 0){
            wrapper.eq("last_user_id", goods.getLastUserId())
                    .set("finish", 1);
        }
        else  wrapper.set("finish", -1);
        return goodsDao.update(null, wrapper);
    }

    @Override
    public int goodsCountLikeName(String search) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("goods_name", search)
                .eq("finish", 0);
        return goodsDao.selectCount(wrapper);
    }

    @Override
    public IPage<Goods> selectGoodsListByName(int current, int size, String search) {

        Page<Goods> page = new Page<>(current, size);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("goods_name", search)
                .eq("finish", 0);
        return goodsDao.selectPage(page, wrapper);
    }


}

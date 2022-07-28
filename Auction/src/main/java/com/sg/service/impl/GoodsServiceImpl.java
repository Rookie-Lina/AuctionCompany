package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.dao.GoodsDao;
import com.sg.dao.GoodsTypeDao;
import com.sg.entity.Goods;
import com.sg.entity.GoodsType;
import com.sg.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private GoodsTypeDao goodsTypeDao;


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
    public IPage<Goods> selectGoodsListNo(int current, int size) {
        Page<Goods> page = new Page<>(current, size);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
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
        if (goods.getLastUserId() != 0) {
            wrapper.eq("last_user_id", goods.getLastUserId())
                    .set("finish", 1);
        } else wrapper.set("finish", -1);
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

    @Override
    public IPage<Goods> selectGoodsListByType(int current, int size, String goodsType) {
        Page<Goods> page = new Page<>(current, size);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        String[] split = goodsType.split("-");
        // 根据 goodsType 的拼接值 选出 第三级类别的id
        if (split[0].equals("3")) {
            wrapper.eq("good_type_id", split[1]);
        } else if(split[0].equals("1")) {
            QueryWrapper<GoodsType> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("first_id",split[1])
                    .eq("grade",3);
            List<GoodsType> goodsTypes = goodsTypeDao.selectList(wrapper1);
            List<Integer> list = new ArrayList<>();
            goodsTypes.forEach(i->list.add(i.getId()));
            wrapper.in("good_type_id",list);
        }else{
            QueryWrapper<GoodsType> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("second_id",split[1])
                    .eq("grade",3);
            List<GoodsType> goodsTypes = goodsTypeDao.selectList(wrapper1);
            List<Integer> list = new ArrayList<>();
            goodsTypes.forEach(i->list.add(i.getId()));
            wrapper.in("good_type_id",list);
        }
        return goodsDao.selectPage(page, wrapper);
    }


}

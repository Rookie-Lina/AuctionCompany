package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.dao.GoodsDao;
import com.sg.dao.GoodsTypeDao;
import com.sg.entity.Goods;
import com.sg.entity.GoodsType;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
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
        } else if (split[0].equals("1")) {
            QueryWrapper<GoodsType> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("first_id", split[1])
                    .eq("grade", 3);
            List<GoodsType> goodsTypes = goodsTypeDao.selectList(wrapper1);
            List<Integer> list = new ArrayList<>();
            goodsTypes.forEach(i -> list.add(i.getId()));
            if (!list.isEmpty())
                wrapper.in("good_type_id", list);
            else
                return page;
        } else {
            QueryWrapper<GoodsType> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("second_id", split[1])
                    .eq("grade", 3);
            List<GoodsType> goodsTypes = goodsTypeDao.selectList(wrapper1);
            List<Integer> list = new ArrayList<>();
            goodsTypes.forEach(i -> list.add(i.getId()));
            wrapper.in("good_type_id", list);
        }
        return goodsDao.selectPage(page, wrapper);
    }

    @Override
    public void selectGoodByUserId(int userId, Page<Goods> page, int finish) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (finish != -4)
            wrapper.eq("finish", finish);
        goodsDao.selectPage(page, wrapper);
    }

    @Override
    public void selectGoodByLastUserId(int lastId, Page<Goods> page) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("last_user_id", lastId);
        goodsDao.selectPage(page, wrapper);
    }

    @Override
    public void addGoods(Goods goods) {
        goods.setFinish(-3);
        goods.setNowPrice(goods.getStartingPrice());
        goodsDao.insert(goods);
    }

    @Override
    public Result findUnCheckedGoodsList(Integer current, Integer pageSize) {
        System.out.println(current);
        System.out.println("----------");
        System.out.println(pageSize);
        IPage<Goods> page = new Page<>(current, pageSize);
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getFinish, -3);
        IPage<Goods> page1 = goodsDao.selectPage(page, lambdaQueryWrapper);
        return new SuccessResult(200, "查询成功", page1);
    }

    @Override
    public Result pass(Integer id, Date date) {
        int update = goodsDao.updateFinishById(id, 0);
        int uodate1 = goodsDao.updateRaseTime(id, date);
        return new SuccessResult(200, "审核通过");
    }

    @Override
    public Result unpass(int id) {
        int update = goodsDao.updateFinishById(id, -2);
        return new SuccessResult(200, "已成功拒绝");
    }

    @Override
    public void reApply(int id) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id)
                .set("finish", -3);
        goodsDao.update(null, wrapper);
    }

    @Override
    public void deleteGoods(int id) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        goodsDao.delete(wrapper);
    }

    @Override
    public void selectGoodByListGoodsId(List<Integer> list, Page<Goods> page) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.in("id", list);
        goodsDao.selectPage(page, wrapper);
    }

}

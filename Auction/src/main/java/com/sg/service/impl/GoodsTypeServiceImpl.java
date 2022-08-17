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
import com.sg.service.GoodsTypeService;
import com.sg.vo.GoodsTypeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Resource
    private GoodsTypeDao goodsTypeDao;

    @Resource
    private GoodsDao goodsDao;

    @Override
    public List<GoodsTypeVo> selectAll() {
        List<GoodsType> goodsTypes = goodsTypeDao.selectList(null);
        List<GoodsTypeVo> list = new ArrayList<>();
        for (GoodsType g1 : goodsTypes) {
            if (g1.getGrade() == 1) {
                GoodsTypeVo goodsTypeVo1 = new GoodsTypeVo();
                goodsTypeVo1.setLabel(g1.getTypeName());
                goodsTypeVo1.setValue(g1.getGrade() + "-" + g1.getId());
                List<GoodsTypeVo> list2 = new ArrayList<>();
                for (GoodsType g2 : goodsTypes) {
                    if (g2.getFirstId() == g1.getId() && g2.getGrade() == 2) {
                        GoodsTypeVo goodsTypeVo2 = new GoodsTypeVo();
                        goodsTypeVo2.setValue(g2.getGrade() + "-" + g2.getId());
                        goodsTypeVo2.setLabel(g2.getTypeName());
                        List<GoodsTypeVo> list3 = new ArrayList<>();
                        for (GoodsType g3 : goodsTypes) {
                            if (g3.getSecondId() == g2.getId() && g3.getGrade() == 3) {
                                GoodsTypeVo goodsTypeVo3 = new GoodsTypeVo();
                                goodsTypeVo3.setValue(g3.getGrade() + "-" + g3.getId());
                                goodsTypeVo3.setLabel(g3.getTypeName());
                                list3.add(goodsTypeVo3);
                            }
                        }
                        goodsTypeVo2.setChildren(list3);
                        list2.add(goodsTypeVo2);
                    }
                }
                goodsTypeVo1.setChildren(list2);
                list.add(goodsTypeVo1);
            }
        }
        return list;
    }

    @Override
    public void addGoodsType(String name, int grade, int parentId) {
        GoodsType goodsType = new GoodsType();
        goodsType.setTypeName(name);
        goodsType.setGrade(grade);
        // 二级
        if (grade == 2) {
            goodsType.setFirstId(parentId);
        }
        // 三级
        if (grade == 3) {
            goodsType.setSecondId(parentId);
            GoodsType goodsType1 = goodsTypeDao.selectById(parentId);
            goodsType.setFirstId(goodsType1.getFirstId());
        }
        goodsTypeDao.insert(goodsType);
    }

    @Override
    public void goodsTypeUpdate(String name, int id) {
        UpdateWrapper<GoodsType> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id)
                .set("type_name", name);
        goodsTypeDao.update(null, wrapper);
    }

    @Override
    public boolean goodsTypeDelete(int id) {
        GoodsType goodsType = goodsTypeDao.selectById(id);
        // 把该类型 全部变成上一级
        if (goodsType.getGrade() == 1)
            return false;

        GoodsType goodsType1 = goodsTypeDao.selectById(id);
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("good_type_id", id);
        if (goodsType.getGrade() == 2) {
            // 查看是否还有子类型
            QueryWrapper<GoodsType> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("second_id", id);
            if (!goodsTypeDao.selectList(queryWrapper).isEmpty())
                return false;
            // 没有 更改所有商品属性为上一级
            wrapper.set("good_type_id", goodsType1.getFirstId());
        }
        if (goodsType.getGrade() == 3)
            wrapper.set("good_type_id", goodsType1.getSecondId());
        goodsDao.update(null,wrapper);
        goodsTypeDao.deleteById(id);
        return true;
    }

    @Override
    public Result goodsTypeListPage(String current, String pageSize) {
        int i = Integer.parseInt(current);
        int i1 = Integer.parseInt(pageSize);
        IPage<GoodsType> iPage=new Page<>(i,i1);
        LambdaQueryWrapper<GoodsType> lambdaQueryWrapper=new LambdaQueryWrapper();
        IPage<GoodsType> iPage1 = goodsTypeDao.selectPage(iPage, lambdaQueryWrapper);
        return new SuccessResult(200,"查询商品类别信息成功",iPage1);
    }

    @Override
    public GoodsType selectById(int id) {
        return goodsTypeDao.selectById(id);
    }
}

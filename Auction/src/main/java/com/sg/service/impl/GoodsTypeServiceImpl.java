package com.sg.service.impl;

import com.sg.dao.GoodsTypeDao;
import com.sg.entity.GoodsType;
import com.sg.service.GoodsTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Resource
    private GoodsTypeDao goodsTypeDao;

    @Override
    public List<GoodsType> selectAll() {
        List<GoodsType> goodsTypes = goodsTypeDao.selectList(null);
        return goodsTypes;
    }
}

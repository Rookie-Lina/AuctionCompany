package com.sg.service.impl;

import com.sg.dao.GoodsDao;
import com.sg.service.GoodsService;
import org.springframework.stereotype.Service;

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

    public Object test(){
        return goodsDao.selectList(null);
    }

}

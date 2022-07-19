package com.sg.controller;

import com.sg.dao.GoodsTypeDao;
import com.sg.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 20:21
 */
@RestController
public class Test0Controller {

    @Resource
    private GoodsService goodsService;

    @Resource
    private GoodsTypeDao goodsTypeDao;

//    @GetMapping("/goods")
    public String goods() {

        System.out.println(goodsService.test());
        System.out.println(goodsService.test());
        System.out.println(goodsService.test());
        System.out.println(goodsTypeDao.selectList(null));
        return "ok";
    }

    @GetMapping("/bbb")
    public String aaa(){
        return "bbb";
    }
}

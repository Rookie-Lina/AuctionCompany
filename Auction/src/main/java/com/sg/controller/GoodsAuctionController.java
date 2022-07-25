package com.sg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.entity.Goods;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-19 10:53
 */
@RestController
@RequestMapping("/goods")
public class GoodsAuctionController {


    Page<Goods> page = new Page<>();
}

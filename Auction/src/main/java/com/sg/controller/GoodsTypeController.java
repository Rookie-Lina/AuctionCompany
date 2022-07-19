package com.sg.controller;

import com.sg.entity.GoodsType;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.GoodsTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/goods-type")
public class GoodsTypeController {

    @Resource
    private GoodsTypeService goodsTypeService;

    @GetMapping("/list")
    public Result queryList() {
        List<GoodsType> goodsTypeList = goodsTypeService.selectAll();
        return new SuccessResult(goodsTypeList);
    }
}

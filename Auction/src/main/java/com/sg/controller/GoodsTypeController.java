package com.sg.controller;

import com.sg.entity.GoodsType;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.GoodsTypeService;
import com.sg.vo.GoodsTypeVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/goods-type")
@PreAuthorize("hasAnyAuthority('NormalUser')")
public class GoodsTypeController {

    @Resource
    private GoodsTypeService goodsTypeService;

    @GetMapping("/list")
    public Result queryList() {
        List<GoodsTypeVo> goodsTypeList = goodsTypeService.selectAll();
        return new SuccessResult(goodsTypeList);
    }
}

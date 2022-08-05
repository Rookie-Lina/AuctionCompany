package com.sg.controller;

import com.sg.result.Result;
import com.sg.result.impl.ErrorResult;
import com.sg.result.impl.SuccessResult;
import com.sg.service.GoodsTypeService;
import com.sg.vo.GoodsTypeVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/goods-type")
//@PreAuthorize("hasAnyAuthority('NormalUser')")
public class GoodsTypeController {

    @Resource
    private GoodsTypeService goodsTypeService;

    @GetMapping("/list")
    public Result queryList() {
        List<GoodsTypeVo> goodsTypeList = goodsTypeService.selectAll();
        return new SuccessResult(goodsTypeList);
    }

    // 新增商品类型
    @PostMapping("/one")
    public Result goodsTypeAdd(String name, int grade, int parentId) {
        goodsTypeService.addGoodsType(name, grade, parentId);
        return new SuccessResult();
    }

    // 修改类型名称
    @PostMapping("/update")
    public Result goodsTypeUpdate(String name, int id) {
        goodsTypeService.goodsTypeUpdate(name, id);
        return new SuccessResult();
    }

    // 删除类型
    @DeleteMapping("/{id}")
    public Result goodsTypeDelete(@PathVariable int id) {
        boolean b = goodsTypeService.goodsTypeDelete(id);
        if (b)
            return new SuccessResult();
        else return new ErrorResult();
    }
}

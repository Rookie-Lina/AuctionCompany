package com.sg.controller;

import com.sg.entity.GoodsType;
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

    // 根据Id查询类型名称
    @GetMapping("{id}")
    public Result queryById(@PathVariable int id){
        GoodsType goodsType = goodsTypeService.selectById(id);
        return new SuccessResult(goodsType);
    }

    @GetMapping("/list")
    public Result queryList() {
        List<GoodsTypeVo> goodsTypeList = goodsTypeService.selectAll();
        return new SuccessResult(goodsTypeList);
    }

    // 新增商品类型
    @PostMapping("/one")
    public Result goodsTypeAdd(String name, String grade, String parentId) {
        int i = Integer.parseInt(grade);
        int i1 = Integer.parseInt(parentId);
        i=i+1;
        goodsTypeService.addGoodsType(name, i, i1);
        return new SuccessResult();
    }

    // 修改类型名称
    @PostMapping("/update")
    public Result goodsTypeUpdate(String name, String id) {
        int i = Integer.parseInt(id);
        goodsTypeService.goodsTypeUpdate(name, i);
        return new SuccessResult();
    }

    // 删除类型
    @GetMapping("/delete")
    public Result goodsTypeDelete( String id) {
        int i = Integer.parseInt(id);
        boolean b = goodsTypeService.goodsTypeDelete(i);
        if (b)
            return new SuccessResult();
        else return new ErrorResult();
    }
    //
    @GetMapping("/goodsTypeListPage")
    public Result goodsTypeListPage(String current,String pageSize){
        return  goodsTypeService.goodsTypeListPage(current,pageSize);
    }
}

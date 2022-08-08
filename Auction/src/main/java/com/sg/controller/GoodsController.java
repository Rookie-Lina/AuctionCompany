package com.sg.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.entity.Goods;
import com.sg.result.Result;
import com.sg.result.impl.ErrorResult;
import com.sg.result.impl.SuccessResult;
import com.sg.service.GoodsService;
import com.sg.vo.GoodsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/goods")
@PreAuthorize("hasAnyAuthority('NormalUser')")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @GetMapping("/ttt")
    public Result ttt(HttpServletRequest request){
        System.out.println("userId: "+request.getAttribute("userId"));
        return new SuccessResult();
    }

    // 添加商品
    @PostMapping("/add")
    public Result addGoods(@RequestBody Goods goods) {
        goodsService.addGoods(goods);
        return new SuccessResult();
    }

    // 查询 所有商品总数
    @GetMapping("/count")
    public Result queryGoodsCountByType(@RequestParam List<Integer> goodsType) {
        System.out.println(goodsType);
        int i = goodsService.goodsCount(goodsType);
        return new SuccessResult(i);
    }

    //查询 分页分类查询商品
    @GetMapping("/list")
    public Result queryGoodsList(int current, String goodsType, String search) {

        IPage<Goods> goodsIPage = null;
        if (Objects.equals(goodsType, "") && Objects.equals(search, ""))
            goodsIPage = goodsService.selectGoodsListNo(current, 4);
        else if (Objects.equals(goodsType, ""))
            goodsIPage = goodsService.selectGoodsListByName(current, 4, search);
        else if (Objects.equals(search, ""))
            goodsIPage = goodsService.selectGoodsListByType(current, 4, goodsType);
        return new SuccessResult(200, "查询成功", goodsIPage);
    }

    //根据ID查询商品信息查询商品信息
    @GetMapping("/id")
    public Result queryGoodById(int id) {
        Goods goods = goodsService.selectGoodById(id);
        if (goods.getFinish() < 0 ) return new ErrorResult("该商品审核中");
        GoodsVo goodsVo = new GoodsVo();
        BeanUtils.copyProperties(goods, goodsVo);
        if (goods.getRaiseTime() != null)
            goodsVo.setRaiseTime(goods.getRaiseTime().getTime());
        return new SuccessResult(goodsVo);
    }

    // 根据卖家Id查询商品信息
    @GetMapping("/user")
    public Result queryGoodByUserId(HttpServletRequest request, long current, int finish) {
        int userId = (int) request.getAttribute("userId");
        Page<Goods> page = new Page<>(current, 4);
        goodsService.selectGoodByUserId(userId, page, finish);
        return new SuccessResult(page);
    }

    // 根据最终拍卖者Id查询商品信息
    @GetMapping("/last-user")
    public Result queryGoodByLastUserId(int lastId, long current, int finish) {
        Page<Goods> page = new Page<>(current, 4);
        goodsService.selectGoodByLastUserId(lastId, page, finish);
        return new SuccessResult(page);
    }

    // 搜索商品
    @GetMapping("/search")
    public Result searchGoods(int current, String search) {
        IPage<Goods> goodsIPage = goodsService.selectGoodsListByName(current, 4, search);
        return new SuccessResult(goodsIPage);
    }

    // 申请重新竞拍
    @PostMapping("/reApply")
    public Result reApply(int id){
        goodsService.reApply(id);
        return new SuccessResult();
    }

    // 删除商品
    @DeleteMapping("/{id}")
    public Result deleteGoods(@PathVariable int id){
        goodsService.deleteGoods(id);
        return new SuccessResult();
    }

}

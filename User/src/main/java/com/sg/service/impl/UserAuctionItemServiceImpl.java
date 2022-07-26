package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.dao.GoodsDao;
import com.sg.entity.Goods;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.UserAuctionItemService;
import com.sg.utilObject.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuctionItemServiceImpl implements UserAuctionItemService {
    @Autowired
    GoodsDao goodsDao;
    @Override
    public Result getAll(Integer currentPage, Integer pageSize) {
        //创建分页对象和查询对象
        Page<Goods> goodsPage=new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Goods> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //获取当前登录的用户信息
        UsernamePasswordAuthenticationToken AuthenticatedUser= (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) AuthenticatedUser.getPrincipal();
        int id = principal.getUser().getId();
        lambdaQueryWrapper.eq(Goods::getUserId,id);
        lambdaQueryWrapper.orderByDesc(Goods::getRaiseTime);
        //查询
        IPage<Goods> goodsIPage = goodsDao.selectPage(goodsPage, lambdaQueryWrapper);
        return new SuccessResult(200,"商品查询成功",goodsIPage);
    }
}

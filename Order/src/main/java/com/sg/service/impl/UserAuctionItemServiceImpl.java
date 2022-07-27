package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.dao.GoodsDao;
import com.sg.dao.OrdersDao;
import com.sg.dao.UserAddressDao;
import com.sg.dao.UserDao;
import com.sg.entity.Goods;
import com.sg.entity.Orders;
import com.sg.entity.UserAddress;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.UserAuctionItemService;
import com.sg.utilObject.LoginUser;
import com.sg.vo.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuctionItemServiceImpl implements UserAuctionItemService {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    OrdersDao ordersDao;
    @Autowired
    UserAddressDao userAddressDao;
    @Autowired
    UserDao userDao;
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

    @Override
    public Result getItemCount() {
        //创建查询对象
        LambdaQueryWrapper<Goods> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //获取当前登录的用户信息
        UsernamePasswordAuthenticationToken AuthenticatedUser= (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) AuthenticatedUser.getPrincipal();
        int id = principal.getUser().getId();
        lambdaQueryWrapper.eq(Goods::getUserId,id);
        Integer integer = goodsDao.selectCount(lambdaQueryWrapper);
        return new SuccessResult(200,"查询拍品总记录数成功！",integer);
    }

    @Override
    public Result getItemDetailByGoodId(Integer goodId) {
        LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<UserAddress> userAddressLambdaQueryWrapper=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper=new LambdaQueryWrapper<>();
        //查出拍卖品的基本信息
        goodsLambdaQueryWrapper.eq(Goods::getId,goodId);
        Goods goods = goodsDao.selectOne(goodsLambdaQueryWrapper);
        int userId = goods.getUserId();
        //查出订单信息
        ordersLambdaQueryWrapper.eq(Orders::getGoodsId,goodId);
        Orders orders = ordersDao.selectOne(ordersLambdaQueryWrapper);
        //查出地址信息
        userAddressLambdaQueryWrapper.eq(UserAddress::getUserId,userId);
        UserAddress userAddress = userAddressDao.selectOne(userAddressLambdaQueryWrapper);
        ReturnObject returnObject=new ReturnObject(goods,orders,userAddress);
        Result result=new SuccessResult(200,"信息查询成功",returnObject);
        return null;
    }
}

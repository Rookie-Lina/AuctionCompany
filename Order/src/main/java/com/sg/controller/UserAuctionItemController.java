package com.sg.controller;

import com.sg.dao.GoodsDao;
import com.sg.result.Result;
import com.sg.service.GoodsService;
import com.sg.service.UserAuctionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户拍卖品
 */
@RestController
@RequestMapping("/AuctionItem")
public class UserAuctionItemController {

    @Autowired
    UserAuctionItemService userAuctionItemService;
    @Autowired
    GoodsDao  goodsDao;
    /**
     * 用户分页查询自己所有的拍品列表
     * @return
     */
    @GetMapping("/GetAuctionItem")
//    @PreAuthorize("hasAnyAuthority('NormalUser')")
    public Result getAuctionItem(Integer currentPage,Integer pageSize){
        //获取用户所有的拍卖品信息
        Result result=userAuctionItemService.getAll(currentPage,pageSize);
        return result;
    }

    /**
     * 查询用户的拍品总数
     * @return
     */
    @GetMapping("/GetItemCount")
    public Result getItemCount(){
        Result result=userAuctionItemService.getItemCount();
        return result;
    }

    /**
     * 查看拍卖品的详细进展
     * @param goodId
     * @return
     */
    @GetMapping("/GetItemDeatail")
    public Result getItemDetail(Integer goodId){
        Result result=userAuctionItemService.getItemDetailByGoodId(goodId);
        return result;
    }
    /**
     * 根据拍卖品品类别查询拍卖品信息
     */
    @GetMapping("/GetItemByType")
    public Result getGoodsByType(Integer typeId,Integer  pagesize){
        Result result= userAuctionItemService.getGoodsByType(typeId,pagesize);
        return result;
    }

}

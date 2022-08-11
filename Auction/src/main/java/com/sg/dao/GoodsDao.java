package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 20:20
 */
@Mapper
public interface GoodsDao extends BaseMapper<Goods> {

    int selectGoodsCount(@Param("goodsType") List<Integer> goodsType);

    int updateFinishById( Integer id,  Integer i);

    int updateRaseTime(Integer id, Date date);
}

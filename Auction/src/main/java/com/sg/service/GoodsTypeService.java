package com.sg.service;

import com.sg.entity.GoodsType;
import com.sg.vo.GoodsTypeVo;

import java.util.List;


public interface GoodsTypeService {
    List<GoodsTypeVo> selectAll();

}

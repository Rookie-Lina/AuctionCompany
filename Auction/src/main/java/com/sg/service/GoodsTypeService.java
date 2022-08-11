package com.sg.service;

import com.sg.result.Result;
import com.sg.vo.GoodsTypeVo;

import java.util.List;


public interface GoodsTypeService {
    List<GoodsTypeVo> selectAll();

    void addGoodsType(String name, int grade, int parentId);

    void goodsTypeUpdate(String name, int id);

    boolean goodsTypeDelete(int id);

    Result goodsTypeListPage(String current, String pageSize);
}


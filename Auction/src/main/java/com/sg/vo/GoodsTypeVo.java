package com.sg.vo;

import lombok.Data;

import java.util.List;


@Data
public class GoodsTypeVo {

    private String value;  // 对应商品类型 id 值
    private String label; // 商品名称
    private List<GoodsTypeVo> children; // 下一级商品
}

package com.sg.entity;

import lombok.Data;

/**
 * @Description 商品类别
 */

@Data
public class GoodsType {

    private int id; // 自增ID
    private String typeName; // 商品类别名称
    private int grade; // 级别
    private int firstId; // 一级菜单ID
    private int secondId; // 二级菜单ID
}

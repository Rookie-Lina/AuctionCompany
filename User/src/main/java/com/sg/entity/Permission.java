package com.sg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("permission")
public class Permission {
    private Integer id;                     //自增id
    private String permissionName;      //权限名称
}

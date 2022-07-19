package com.sg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("roles")
public class Role {

    private int id; // 自增ID
    private int roleId; // -1超级管理员0普通管理员1普通用户
    private String roleName; // 角色名称
}

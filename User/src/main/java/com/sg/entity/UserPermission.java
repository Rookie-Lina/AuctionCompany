package com.sg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_permission")
public class UserPermission {
    private Integer id;             //自增主键
    private Integer userId;         //用户id
    private Integer permissionId;    //权限id
}

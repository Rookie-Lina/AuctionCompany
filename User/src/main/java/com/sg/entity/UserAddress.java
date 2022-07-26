package com.sg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users_address")
public class UserAddress {
    private int id;//地址的自增 id
    private int userId;//关联用户的id
    private String address;//详细地址
    private String phone;//电话
    private String name;//名称
}

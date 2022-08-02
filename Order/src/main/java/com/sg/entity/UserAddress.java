package com.sg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 11:03
 */
@Data
@TableName("users_address")
public class UserAddress {

    private int id;
    private int userId;
    private String address;
    private String phone;
    private String name;
}

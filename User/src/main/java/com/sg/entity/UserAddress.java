package com.sg.entity;

import lombok.Data;

@Data
public class UserAddress {
    private int id;//地址的自增 id
    private int userId;//关联用户的id
    private String address;//详细地址
}

package com.sg.vo;

import lombok.Data;

import java.util.Date;
@Data
public class UserVo {
    private int id; // 自增ID
    private String username; // 账号
    private String password; // 密码
}

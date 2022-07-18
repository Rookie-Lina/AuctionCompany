package com.sg.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 18:11
 */
@Data
public class Staffs {

    private int id; // 自增ID
    private String loginName; // 账号
    private String loginPwd; // 密码
    private String staffName; // 职员名称
    private String staffPhoto; // 职员头像
    private int workStatus; // 工作状态
    private int staffStatus; // 职员状态
    private String staffPhone; // 职员手机
    private Date createTime; // 创建时间

}

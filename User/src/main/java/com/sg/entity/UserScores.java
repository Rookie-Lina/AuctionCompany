package com.sg.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 18:11
 */
@Data
public class UserScores {

    private int id; // 自增Id
    private int userId; // 用户Id
    private int score; // 积分数
    private int dataSrc; // 积分来源
    private int dataId; // 来源记录id
    private int scoreType; //积分标识
    private Date createTime; // 创建时间
}

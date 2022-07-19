package com.sg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("user_ranks")
public class UserRank {

    private int id; // 自增ID
    private String rankName; // 等级名称
    private int startScore; // 开始积分
    private int endScore; // 结束积分
    private double eBate; // 折扣
    private String userRankImg; // 等级图标
    private Date createTime; // 创建时间

}

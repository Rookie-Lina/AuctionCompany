package com.sg.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("users")
public class User {

    private int id; // 自增ID
    private String loginName; // 账号
    private String loginPwd; // 密码
    private int userSex; // 性别
    private String userName; // 用户名称
    private String trueName; // 真实姓名
    private Date birthday; // 生日
    private String userPhoto; // 会员头像
    private String userEmail; // 邮箱
    private int userScore; // 用户购物积分
    private int roleId; // 角色Id
    private String userPhone; // 手机号码
    private int saleScore;//用户拍卖商品所得的积分
}
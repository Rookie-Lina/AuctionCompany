package entity;


import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 18:10
 */
@Data
public class Users {

    private int userId; // 自增ID
    private String loginName; // 账号
    private String loginPwd; // 密码
    private int userSex; // 性别
    private String userName; // 用户名称
    private String trueName; // 真实姓名
    private Date birthday; // 生日
    private String userPhoto; // 会员头像
    private String userEmail; // 邮箱
    private int userScore; // 用户积分
    private int roleId; // 角色Id
    private String userPhone; // 手机号码
}

package entity;

import lombok.Data;

/**
 * @Description 角色表
 * @auther Rookie_lin
 * @create 2022-07-18 18:11
 */
@Data
public class Roles {

    private int id; // 自增ID
    private int roleId; // -1超级管理员0普通管理员1普通用户
    private String roleName; // 角色名称
}

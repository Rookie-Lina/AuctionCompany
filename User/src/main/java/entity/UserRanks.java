package entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 18:11
 */
@Data
public class UserRanks {

    private int rankId; // 自增ID
    private String rankName; // 等级名称
    private int startScore; // 开始积分
    private int endScore; // 结束积分
    private double eBate; // 折扣
    private String userRankImg; // 等级图标
    private Date createTime; // 创建时间

}

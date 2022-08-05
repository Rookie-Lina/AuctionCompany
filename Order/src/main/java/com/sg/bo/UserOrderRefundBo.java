package com.sg.bo;

import com.sg.entity.OrderRefunds;
import com.sg.entity.User;
import lombok.Data;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-28 15:18
 */
@Data
public class UserOrderRefundBo {

    private User user;
    private OrderRefunds orderRefunds;
}

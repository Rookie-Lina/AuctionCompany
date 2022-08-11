package com.sg.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class GoodsTimeVo implements Serializable {
    private Integer id;//商品id
    private Date raiseTime;//开始拍卖的时间
}

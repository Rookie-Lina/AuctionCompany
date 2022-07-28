package com.sg.vo;

import com.sg.entity.Goods;
import com.sg.entity.Orders;
import com.sg.entity.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnObject implements Serializable {
    private Goods goods;
    private Orders  orders;
    private UserAddress address;
}

package com.sg.utilObject;

import com.sg.entity.Goods;
import com.sg.entity.Orders;
import lombok.Data;
import sun.jvm.hotspot.debugger.Address;

@Data
public class ReturnObject {
    private Goods goods;
    private Orders  orders;
    private Address  address;
}

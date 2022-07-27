package com.sg.utilObject;

import com.sg.entity.Goods;
import com.sg.entity.Orders;
import com.sg.entity.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.jvm.hotspot.debugger.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnObject {
    private Goods goods;
    private Orders  orders;
    private UserAddress address;
}

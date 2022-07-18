package com.sg.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Result implements Serializable {
    // "状态编码"
    private Integer code;

    // "提示信息"
    private String message;

    // "返回结果"
    private Object data;

    public Result(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}
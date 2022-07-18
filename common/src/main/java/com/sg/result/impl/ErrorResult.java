package com.sg.result.impl;


import com.sg.enums.ResultEnum;
import com.sg.result.Result;

/**
 * 异常结果统一封装类
 */
public class ErrorResult extends Result {
    public ErrorResult(){
        super(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getValue());
    }

    public ErrorResult(String message){
        super(ResultEnum.ERROR.getCode(), message);
    }

    public ErrorResult(Integer code, String message){
        super(code, message);
    }

    public ErrorResult(Integer code, String message, Object data){
        super(code, message, data);
    }

    public ErrorResult(Object data){
        super(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getValue(), data);
    }
}

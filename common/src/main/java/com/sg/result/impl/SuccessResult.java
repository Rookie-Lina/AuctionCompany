package com.sg.result.impl;


import com.sg.enums.ResultEnum;
import com.sg.result.Result;

/**
 * 成功结果统一封装类
 */
public class SuccessResult extends Result {
    public SuccessResult(){
        super(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getValue());
    }

    public SuccessResult(String message){
        super(ResultEnum.SUCCESS.getCode(), message);
    }

    public SuccessResult(Integer code, String message){
        super(code, message);
    }

    public SuccessResult(Integer code, String message, Object data){
        super(code, message, data);
    }

    public SuccessResult(String message, Object data){
        super(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public SuccessResult(Object data){
        super(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getValue(), data);
    }
}

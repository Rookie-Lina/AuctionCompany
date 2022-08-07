package com.sg.hander;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-04 21:53
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(InvalidException.class)
    public Result error(InvalidException e){
        e.printStackTrace();
        return new SuccessResult(401,"登录失效");
    }

    @ExceptionHandler(IllegalityException.class)
    public Result error(IllegalityException e){
        e.printStackTrace();
        return new SuccessResult(401,"token 非法");
    }
}

package com.sg.handler;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-07 22:18
 */

import com.alibaba.fastjson.JSON;
import com.sg.result.impl.SuccessResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 - 自定义授权异常处理类
 */
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(new SuccessResult(403,"权限不足")));
    }
}

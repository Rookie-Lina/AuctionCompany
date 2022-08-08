package com.sg.filter;

import com.sg.entity.RedisCache;
import com.sg.result.impl.SuccessResult;
import com.sg.util.JwtUtil;
import com.sg.util.RenderUtils;
import com.sg.utilObject.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        System.out.println("------"+token);
        if (!StringUtils.hasText(token)||token.equals("undefined")) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userLoginName;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userLoginName = claims.getSubject();
        } catch (Exception e) {
            RenderUtils.renderJson(response,new SuccessResult(401,"token过期"));
            return;
        }
        //从redis中获取用户信息
        String redisKey = "login:" + userLoginName;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            RenderUtils.renderJson(response,new SuccessResult(401,"token过期"));
            return;
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        request.setAttribute("userId",loginUser.getUser().getId());
        //放行
        filterChain.doFilter(request, response);
    }
}
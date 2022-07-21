package com.sg.service.impl;

import com.sg.entity.RedisCache;
import com.sg.entity.User;
import com.sg.result.Result;
import com.sg.result.impl.SuccessResult;
import com.sg.service.LoginService;
import com.sg.util.JwtUtil;
import com.sg.utilObject.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public Result login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getLoginName(),user.getLoginPwd());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //使用账号 生成token
        LoginUser loginUser= (LoginUser) authenticate.getPrincipal();
        String jwt = JwtUtil.createJWT(loginUser.getUser().getLoginName());
        Map<String,String> map=new HashMap<>();
        map.put("token",jwt);
//        //authenticate存入redis
        redisCache.setCacheObject("login:"+loginUser.getUser().getLoginName(),loginUser);
//        //把token响应给前端
        return new SuccessResult(200,"登录成功！",jwt);
    }

    @Override
    public Result logout() {
        //获取SecurityContextHolder中的用户账号
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String loginName = loginUser.getUser().getLoginName();
        //删除redis中的数据
        redisCache.deleteObject("login:"+loginName);
        return new SuccessResult(200,"登出成功");
    }
}

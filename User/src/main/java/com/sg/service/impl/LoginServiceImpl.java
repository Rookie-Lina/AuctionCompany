package com.sg.service.impl;

import com.sg.entity.RedisCache;
import com.sg.entity.User;
import com.sg.result.Result;
import com.sg.service.LoginService;
import com.sg.util.JwtUtil;
import com.sg.utilObject.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        //使用userid生成token
//        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
//        String userId = loginUser.getUser().getId().toString();
//        String jwt = JwtUtil.createJWT(userId);
//        //authenticate存入redis
//        redisCache.setCacheObject("login:"+userId,loginUser);
//        //把token响应给前端
//        HashMap<String,String> map = new HashMap<>();
//        map.put("token",jwt);
//        return new ResponseResult(200,"登陆成功",map);
        return null;
    }
}

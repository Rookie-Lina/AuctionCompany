package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.dao.UserDao;
import com.sg.dao.UserPermissionDao;
import com.sg.entity.RedisCache;
import com.sg.entity.User;
import com.sg.entity.UserPermission;
import com.sg.result.Result;
import com.sg.result.impl.ErrorResult;
import com.sg.result.impl.SuccessResult;
import com.sg.service.UserService;
import com.sg.util.JwtUtil;
import com.sg.utilObject.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserPermissionDao userPermissionDao;
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
    //注册功能
    //此方法目前不要加事务
    @Override
    public Result register(User user) {
        //检测账号是否为空,为空则返回错误
        if(user==null||user.getLoginName()==null){
            return new ErrorResult(403,"无用户信息，请重新注册");
        }
        //检验账号是否已经被占用了
        String loginName = user.getLoginName();
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getLoginName,loginName);
        User user1 = userDao.selectOne(lambdaQueryWrapper);
        if(user1!=null){
            return new ErrorResult(403,"该用户名已被注册");
        }
        //对用户的密码进行加密
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String loginPwd = user.getLoginPwd();
        String encode = bCryptPasswordEncoder.encode(loginPwd);
        user.setLoginPwd(encode);
        //设置权限id
        user.setRoleId(1);
        //存入数据库
        userDao.insert(user);
        //授予用户权限信息
        userPermissionDao.setauthorityToNomallUser(user.getId(),3);
        return new SuccessResult(200,"注册成功",user.getLoginName());
    }
}

package com.sg.controller;

import com.sg.entity.User;
import com.sg.result.Result;
import com.sg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录接口
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }

    /**
     * 登出接口
     * @return
     */
    @RequestMapping("/user/logout")
    public Result logout(){
        return userService.logout();
    }
    @PostMapping("/user/register")
    public Result register(@RequestBody User user){
        return userService.register(user);
    }
}

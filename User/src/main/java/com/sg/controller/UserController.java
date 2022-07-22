package com.sg.controller;

import com.sg.entity.User;
import com.sg.result.Result;
import com.sg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }
    @RequestMapping("/user/logout")
    public Result logout(){
        return userService.logout();
    }
}

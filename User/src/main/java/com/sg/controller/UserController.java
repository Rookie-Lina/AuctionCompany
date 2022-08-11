package com.sg.controller;

import com.sg.entity.User;
import com.sg.result.Result;
import com.sg.service.UserService;
import com.sg.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 登录接口
     * @param
     * @return
     */
//    @PostMapping("/user/login")
//    public Result login(@RequestBody User user){
//        return userService.login(user);
//    }
    @GetMapping("/user/login")
    public Result login(String username,String password){
        User user=new User();
        user.setLoginName(username);
        user.setLoginPwd(password);
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

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/user/register")
    public Result register(@RequestBody User user){
        return userService.register(user);
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @DeleteMapping("/user/delete")
    @PreAuthorize("hasAuthority('SuperAdmin')")
    public Result  deleteUserByLoginName(@RequestBody User user){
        return userService.deleteUserByLoginName(user);
    }

    /**
     * 分页查看用户信息
     * @param current
     * @param pageSize
     * @return
     */
    @GetMapping("/user/info")
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    public Result getUsers(String current,String pageSize){
        Integer c=Integer.parseInt(current);
        Integer ps=Integer.parseInt(pageSize);
        return userService.getUsers(c,ps);
    }

    /**
     * 根据用户id查看用户信息
     * @param id
     * @return
     */
    @GetMapping("/user/getUserById")
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    public Result getUserById(String id){
        System.out.println(id);
        int i = Integer.parseInt(id);
        return userService.getUserById(i);
    }

    /**
     * 根据用户id删除用户信息
     * @param id
     * @return
     */
    @GetMapping("/user/deleteUserById")
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    public Result deleteUserById(String id){
        System.out.println(id);
        int i = Integer.parseInt(id);
        return userService.deleteUserById(i);
    }
    @PostMapping("/user/updateUserInfo")
    public Result updateUserInfo(@RequestBody User user){
     return  userService.updateUserInfo(user);
    }
    //管理员添加用户
    @PostMapping("/user/addUser")
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    public Result addUserByAdmin(@RequestBody User user){
        return userService.addUser(user);
    }
}

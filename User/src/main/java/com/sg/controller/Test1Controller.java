package com.sg.controller;

import com.sg.dao.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 20:39
 */
@RestController
public class Test1Controller {

    @Resource
    private RolesDao rolesDao;

    @Resource
    private StaffsDao staffsDao;

    @Resource
    private UserRanksDao userRanksDao;

    @Resource
    private UsersDao usersDao;

    @Resource
    private UserScoresDao userScoresDao;

    @GetMapping("/users")
    public String users(){
        System.out.println(rolesDao.selectList(null));
        System.out.println(staffsDao.selectList(null));
        System.out.println(userRanksDao.selectList(null));
        System.out.println(usersDao.selectList(null));
        System.out.println(userScoresDao.selectList(null));
        return "ok";
    }
}

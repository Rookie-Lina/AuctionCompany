package com.sg.controller;

import com.sg.dao.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class Test1Controller {

    @Resource
    private RoleDao roleDao;

    @Resource
    private StaffDao staffsDao;

    @Resource
    private UserRankDao userRankDao;

    @Resource
    private UserDao usersDao;

    @Resource
    private UserScoreDao userScoreDao;
    @GetMapping("/users")
    public String users(){
        System.out.println(roleDao.selectList(null));
        System.out.println(staffsDao.selectList(null));
        System.out.println(userRankDao.selectList(null));
        System.out.println(usersDao.selectList(null));
        System.out.println(userScoreDao.selectList(null));
        return "ok";
    }
}

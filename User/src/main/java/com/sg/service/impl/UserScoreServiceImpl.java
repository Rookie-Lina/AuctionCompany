package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sg.dao.UserDao;
import com.sg.dao.UserScoreDao;
import com.sg.entity.User;
import com.sg.entity.UserScore;
import com.sg.service.UserScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-25 14:58
 */
@Service
public class UserScoreServiceImpl implements UserScoreService {

    @Resource
    private UserScoreDao userScoreDao;

    @Resource
    private UserDao userDao;

    @Override
    public void orderApplyScore(int orderId, int userId,double price) {
        UserScore userScore = new UserScore();
        userScore.setUserId(userId);
        userScore.setDataId(orderId);
        userScore.setCreateTime(new Date());
        userScore.setDataSrc(1);
        userScore.setScoreType(1);
        userScore.setScore((int) price);
        userScoreDao.insert(userScore);
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",userId)
                .setSql(" user_score = user_score + "+(int) price);
        userDao.update(null,wrapper);
    }
}

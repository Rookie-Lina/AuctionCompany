package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.UserScores;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 20:38
 */
@Mapper
public interface UserScoresDao extends BaseMapper<UserScores> {
}

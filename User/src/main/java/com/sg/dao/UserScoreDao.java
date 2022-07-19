package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.UserScore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserScoreDao extends BaseMapper<UserScore> {
}

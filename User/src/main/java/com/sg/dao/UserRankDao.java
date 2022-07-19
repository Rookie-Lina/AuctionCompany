package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.UserRank;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserRankDao extends BaseMapper<UserRank> {
}

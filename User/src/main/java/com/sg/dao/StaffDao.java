package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.Staff;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StaffDao extends BaseMapper<Staff> {
}

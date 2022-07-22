package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.UserPermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPermissionDao extends BaseMapper<UserPermission> {
}

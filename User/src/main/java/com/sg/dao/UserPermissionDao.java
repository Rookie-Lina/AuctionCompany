package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserPermissionDao extends BaseMapper<UserPermission> {
    void setauthorityToNomallUser(@Param("id") int id, @Param("pid") int pid);
}

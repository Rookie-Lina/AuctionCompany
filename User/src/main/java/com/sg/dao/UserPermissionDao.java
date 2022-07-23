package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserPermissionDao extends BaseMapper<UserPermission> {
    /**
     * 给普通用户设置权限
     * @param id
     * @param pid
     */
    void setauthorityToNomallUser(@Param("id") int id, @Param("pid") int pid);

    /**
     * 根据用户id删除用户已有权限
     * @param uid
     */
    void deleteByUserId(Integer uid);
}

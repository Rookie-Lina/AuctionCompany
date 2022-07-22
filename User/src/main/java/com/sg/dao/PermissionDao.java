package com.sg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDao extends BaseMapper<Permission> {
    /**
     * 根据用户id查出用户拥有的权限
     * @param uid
     * @return
     */
    List<String> getPermissionByUserId(Integer uid);
}


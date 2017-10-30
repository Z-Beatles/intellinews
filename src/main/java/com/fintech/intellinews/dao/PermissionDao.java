package com.fintech.intellinews.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionDao {

    /**
     * 根据角色名查询权限列表
     *
     * @param role 角色名
     * @return 权限列表
     */
    List<String> listByRoleName(@Param("name") String role);
}
package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao extends BaseDao<RoleEntity> {
    /**
     * 根据用户id查询角色列表
     *
     * @param loginUserId 用户id
     * @return 角色列表
     */
    List<String> listByLoginUserId(@Param("id") Long loginUserId);
}
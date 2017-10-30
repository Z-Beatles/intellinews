package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserLoginEntity;

public interface UserLoginDao extends BaseDao<UserLoginEntity> {

    /**
     * 根据用户名查询用户登录信息
     *
     * @param username 用户名
     * @return 用户登录信息
     */
    UserLoginEntity selectByUserName(String username);
}
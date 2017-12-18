package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserInfoEntity;

import java.util.List;

public interface UserInfoDao {

    /**
     * 获取用户信息
     *
     * @param userInfo 用户信息对象
     * @return 用户信息对象
     */
    List<UserInfoEntity> listUserInfos(UserInfoEntity userInfo);

    /**
     * 添加用户信息
     *
     * @param userInfoEntity 用户信息对象
     */
    void insertUserInfo(UserInfoEntity userInfoEntity);
}
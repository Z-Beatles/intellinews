package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserInfoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoDao {
    /**
     * 获取用户信息
     *
     * @param userInfo 用户信息对象
     * @return 用户信息对象
     */
    List<UserInfoEntity> listUserInfos(UserInfoEntity userInfo);

    void insertUserInfo(UserInfoEntity userInfoEntity);
}
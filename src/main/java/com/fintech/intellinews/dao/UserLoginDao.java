package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserLoginEntity;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserLoginDao {

    /**
     * 根据用户id列表获取用户登录信息列表
     *
     * @param list 用户id列表
     * @return 用户登录信息列表
     */
    @MapKey("id")
    Map<Long, UserLoginEntity> mapUserLoginByIds(List<Long> list);

    /**
     * 获取用户登录信息列表
     *
     * @param userLoginEntity 户登录信息对象
     * @return 用户登录信息列表
     */
    List<UserLoginEntity> listUserLogins(UserLoginEntity userLoginEntity);

    /**
     * 添加用户登录信息
     *
     * @param userLoginEntity 用户登录信息对象
     */
    void insertUserLogin(UserLoginEntity userLoginEntity);

    /**
     * 根据用户id获取登录信息
     *
     * @param id 用户id
     * @return 登录信息对象
     */
    UserLoginEntity getUserLoginById(Long id);
}
package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserLoginEntity;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserLoginDao {

    @MapKey("id")
    Map<Long, UserLoginEntity> mapUserLoginByIds(List<Long> list);

    List<UserLoginEntity> listUserLogins(UserLoginEntity userLoginEntity);

    void insertUserLogin(UserLoginEntity userLoginEntity);

    UserLoginEntity getUserLoginById(Long id);
}
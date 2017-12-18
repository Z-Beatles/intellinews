package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserKeywordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanghao
 * create 2017-11-23 11:22
 **/
public interface UserKeywordDao {

    List<UserKeywordEntity> getUserKeywords(@Param("userId") Long userId);

    Integer addUserKeyword(UserKeywordEntity entity);

    Integer updateHobbyAttention(UserKeywordEntity entity);
}

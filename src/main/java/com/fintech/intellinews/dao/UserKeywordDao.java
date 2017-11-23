package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserKeywordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wanghao
 * create 2017-11-23 11:22
 **/
public interface UserKeywordDao extends BaseDao<UserKeywordEntity> {

    List<UserKeywordEntity> getUserKeywords(@Param("userId")Long userId);

    Integer addUserKeyword(UserKeywordEntity entity);

    Integer updateHobbyAttention(UserKeywordEntity entity);
}

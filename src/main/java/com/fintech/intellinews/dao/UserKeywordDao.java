package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserKeywordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghao
 * create 2017-11-23 11:22
 **/
@Repository
public interface UserKeywordDao extends BaseDao<UserKeywordEntity> {

    List<UserKeywordEntity> getUserKeywords(@Param("userId") Long userId);

    Integer addUserKeyword(UserKeywordEntity entity);

    Integer updateHobbyAttention(UserKeywordEntity entity);
}

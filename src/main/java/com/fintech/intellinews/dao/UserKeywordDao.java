package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserKeywordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanghao
 * create 2017-11-23 11:22
 **/
public interface UserKeywordDao {

    /**
     * 获取用户关键字
     *
     * @param userId 用户id
     * @return 关键字列表
     */
    List<UserKeywordEntity> getUserKeywords(@Param("userId") Long userId);

    /**
     * 添加用户关键字
     *
     * @param entity 关键字对象
     * @return 受影响的行数
     */
    Integer addUserKeyword(UserKeywordEntity entity);

    /**
     * 更新用户关键字
     *
     * @param entity 关键字对象
     * @return 受影响的行数
     */
    Integer updateHobbyAttention(UserKeywordEntity entity);
}

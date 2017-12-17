package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserConfigEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserConfigDao {
    /**
     * 根据id获取用户的频道配置
     *
     * @param userId 用户id
     * @return 频道配置
     */
    String getUserChannelConfig(Long userId);

    /**
     * 初始化用户频道配置
     *
     * @param userId        用户id
     * @param channelConfig 默认配置
     * @return 受影响的行数
     */
    Integer insertUserConfig(@Param("userId") Long userId, @Param("channelConfig") String channelConfig);

    List<UserConfigEntity> getSortEntity();

    /**
     * 更新用户频道配置
     *
     * @param entity 用户配置对象
     * @return 受影响的行数
     */
    Integer updateUserConfig(UserConfigEntity entity);
}
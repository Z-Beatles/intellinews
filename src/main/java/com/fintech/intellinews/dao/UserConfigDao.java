package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserConfigEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserConfigDao extends BaseDao<UserConfigEntity> {
    /**
     * 查询当前用户的频道配置
     *
     * @param userId 用户id
     * @return 频道配置
     */
    String getUserChannelConfig(Long userId);

    /**
     * 添加用户频道默认配置
     *
     * @param userId        用户id
     * @param channelConfig 频道配置
     * @param gmtCreate     创建时间
     * @return 受影响的行数
     */
    Integer insertUserConfig(@Param("userId") Long userId, @Param("channelConfig") String channelConfig, @Param
            ("gmtCreate") Date gmtCreate);

    List<UserConfigEntity> getSortEntity();
}
package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserSectionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSectionDao {

    /**
     * 根据用户id获取用户收藏的条目列表
     *
     * @param userId 用户id
     * @return 条目列表
     */
    List<UserSectionEntity> getUserSection(Long userId);

    /**
     * 获取用户收藏的指定条目
     *
     * @param entity 条目收藏对象
     * @return 条目收藏对象
     */
    UserSectionEntity getUserSectionCollect(UserSectionEntity entity);

    /**
     * 删除收藏的条目
     *
     * @param entity 条目收藏对象
     * @return 受影响的行数
     */
    Integer deleteCollectSection(UserSectionEntity entity);

    /**
     * 判断用户是否收藏
     *
     * @param userId    用户id
     * @param sectionId 条目id
     * @return 受影响的行数
     */
    Integer checkUserSection(@Param("userId") Long userId, @Param("sectionId") Long sectionId);

    /**
     * 用户收藏条目
     *
     * @param insertSection 收藏条目对象
     * @return 受影响的行数
     */
    Integer insertUserSection(UserSectionEntity insertSection);
}
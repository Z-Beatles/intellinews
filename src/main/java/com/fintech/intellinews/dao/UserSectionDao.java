package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserSectionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSectionDao {
    /**
     * 根据用户id获取用户收藏的条目列表
     *
     * @param userId 用户id
     * @return 条目列表
     */
    List<UserSectionEntity> getUserSection(Long userId);

    UserSectionEntity getUserSectionCollect(UserSectionEntity entity);

    Integer deleteCollectSection(UserSectionEntity entity);

    Integer checkUserSection(@Param("userId") Long userId, @Param("sectionId") Long sectionId);

    /**
     * 用户收藏条目
     *
     * @param insertSection 收藏条目对象
     * @return 受影响的行数
     */
    Integer insertUserSection(UserSectionEntity insertSection);
}
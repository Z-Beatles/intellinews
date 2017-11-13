package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserSectionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSectionDao extends BaseDao<UserSectionEntity> {
    /**
     * 获取用户收藏条目
     * @param userId 用户id
     * @return 条目列表
     */
    List<UserSectionEntity> getUserSection(Long userId);
}
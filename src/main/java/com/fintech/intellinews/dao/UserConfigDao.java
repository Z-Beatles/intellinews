package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserArticleEntity;
import com.fintech.intellinews.entity.UserConfigEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConfigDao extends BaseDao<UserConfigEntity> {
    /**
     * 查询当前用户的频道列表
     * @param userId
     * @return
     */
    UserConfigEntity getCurrentUserConfig(Long userId);
}
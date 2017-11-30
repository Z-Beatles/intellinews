package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.ArticleChannelEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArticleChannelDao extends BaseDao<ArticleChannelEntity> {

    /**
     * 根据频道id查询文章频道信息
     *
     * @param channelId 频道id
     * @return 文章的频道信息
     */
    List<ArticleChannelEntity> listByChannelId(Long channelId);
}

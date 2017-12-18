package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.ArticleChannelEntity;

import java.util.List;

public interface ArticleChannelDao {

    /**
     * 根据频道id查询文章频道信息
     *
     * @param channelId 频道id
     * @return 文章的频道信息
     */
    List<ArticleChannelEntity> listByChannelId(Long channelId);
}

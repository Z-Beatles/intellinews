package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

public class ArticleChannelEntity extends BaseEntity {
    private Long id;

    private Long channelId;

    private Long articleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
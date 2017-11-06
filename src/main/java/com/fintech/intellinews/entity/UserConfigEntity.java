package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

public class UserConfigEntity extends BaseEntity {
    private Long id;

    private Long userId;

    private String channelConfig;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getChannelConfig() {
        return channelConfig;
    }

    public void setChannelConfig(String channelConfig) {
        this.channelConfig = channelConfig == null ? null : channelConfig.trim();
    }
}
package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

public class FootmarkEntity extends BaseEntity {
    private Long id;

    private Long userId;

    private Long footmarkId;

    private Integer footmarkType;

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

    public Long getFootmarkId() {
        return footmarkId;
    }

    public void setFootmarkId(Long footmarkId) {
        this.footmarkId = footmarkId;
    }

    public Integer getFootmarkType() {
        return footmarkType;
    }

    public void setFootmarkType(Integer footmarkType) {
        this.footmarkType = footmarkType;
    }
}
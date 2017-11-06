package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

public class UserSectionEntity extends BaseEntity {
    private Long id;

    private Long sectionId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
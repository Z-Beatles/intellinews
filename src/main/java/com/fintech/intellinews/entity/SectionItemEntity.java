package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

public class SectionItemEntity extends BaseEntity {
    private Long id;

    private Long sectionId;

    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
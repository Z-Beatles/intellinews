package com.fintech.intellinews.entity;

/**
 * @author waynechu
 * Created 2017-11-14 11:06
 */
public class SectionItemEntity {
    private Long id;

    private Long sectionId;

    private String itemInfo;

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

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }
}

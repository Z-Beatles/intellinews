package com.fintech.intellinews.entity;

/**
 * @author waynechu
 * Created 2017-11-12 13:01
 */
public class SectionAliasEntity {
    private Long id;

    private Long sectionId;

    private String alias;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}

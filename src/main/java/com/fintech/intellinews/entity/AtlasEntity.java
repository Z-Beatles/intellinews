package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

/**
 * @author waynechu
 * Created 2017-11-22 12:22
 */
public class AtlasEntity extends BaseEntity {
    private Long id;

    private Long sectionId;

    private Long relationId;

    private String relationType;

    private int relationDegree;

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

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public int getRelationDegree() {
        return relationDegree;
    }

    public void setRelationDegree(int relationDegree) {
        this.relationDegree = relationDegree;
    }
}

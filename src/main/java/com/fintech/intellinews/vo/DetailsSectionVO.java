package com.fintech.intellinews.vo;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Date;

/**
 * @author waynechu
 * Created 2017-11-11 23:22
 */
public class DetailsSectionVO {
    private Long id;

    private String logo;

    private String name;

    private String createBy;

    private String updateBy;

    private Date updateTime;

    private String introduction;

    private Integer viewCount;

    private Integer shareCount;

    private Integer collectCount;

    private ArrayNode itemInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public ArrayNode getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ArrayNode itemInfo) {
        this.itemInfo = itemInfo;
    }
}

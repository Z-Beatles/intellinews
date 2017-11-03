package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

/**
 * @author waynechu
 * Created 2017-10-27 13:58
 */
public class AdvertiseEntity extends BaseEntity {
    /**
     * 广告id
     */
    private Long id;
    /**
     * 广告是否上架
     */
    private Boolean active;
    /**
     * 广告标题
     */
    private String title;
    /**
     * 广告宣传图
     */
    private String imgUrl;
    /**
     * 广告链接地址
     */
    private String linkUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}

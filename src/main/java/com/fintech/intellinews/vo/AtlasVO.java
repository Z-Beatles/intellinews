package com.fintech.intellinews.vo;

/**
 * @author waynechu
 * Created 2017-11-22 14:00
 */
public class AtlasVO {
    /** 文章/条目id */
    private Long id;
    /** 文章/条目标题 */
    private String title;
    /** 相关度 1-375 */
    private Integer distance;
    /** 权重 1-50 */
    private Integer weight;
    /** 条目logo */
    private String logo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

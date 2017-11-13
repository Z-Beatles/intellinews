package com.fintech.intellinews.vo;

/**
 * @author waynechu
 * Created 2017-11-11 20:39
 */
public class ListSectionVO {
    /** 条目id */
    private Long id;
    /** 条目logo */
    private String logo;
    /** 条目名称 */
    private String name;
    /** 条目浏览量 */
    private Integer viewCount;

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

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}

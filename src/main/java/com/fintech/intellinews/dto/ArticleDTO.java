package com.fintech.intellinews.dto;

/**
 * @author waynechu
 * Created 2017-10-31 12:35
 */
public class ArticleDTO {
    /** 文章id */
    private Long id;
    /** 文章标题 */
    private String title;
    /** 文章来源 */
    private String source;
    /** 发布时间 */
    private String date;
    /** 关键字 */
    private String keywords;
    /** 浏览量 */
    private Integer viewCount;
    /** 文章缩略图 */
    private String thumbnail;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

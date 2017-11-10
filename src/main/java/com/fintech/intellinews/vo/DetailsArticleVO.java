package com.fintech.intellinews.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author wanghao
 * create 2017-11-10 14:31
 **/
public class DetailsArticleVO {
    private Long id;

    private String title;

    private String source;

    private String thumbnail;

    private String keywords;

    private String section;

    private String content;

    private String date;

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String timeDesc) {
        this.date = timeDesc;
    }

}

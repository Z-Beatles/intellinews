package com.fintech.intellinews.vo;

/**
 * @author waynechu
 * Created 2017-10-31 17:56
 */
public class ColumnVO {
    /** 专栏id */
    private Long id;
    /** 专栏名称 */
    private String name;
    /** 专栏缩略图 */
    private String thumbnail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

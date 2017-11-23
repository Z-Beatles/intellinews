package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

/**
 * @author wanghao
 * create 2017-11-23 11:08
 **/
public class UserKeywordEntity extends BaseEntity {
    private Long id;
    private Long userId;
    private String keyword;
    private Integer attention;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }
}

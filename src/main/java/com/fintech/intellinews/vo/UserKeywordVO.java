package com.fintech.intellinews.vo;

import java.io.Serializable;

/**
 * @author wanghao
 * create 2017-11-23 11:32
 **/
public class UserKeywordVO implements Serializable {
    private Long id;
    private String keyword;
    private Integer attention;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

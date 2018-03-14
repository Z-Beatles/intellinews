package com.fintech.intellinews.vo;

import java.io.Serializable;

/**
 * @author waynechu
 * Created 2017-11-14 13:15
 */
public class SearchSectionVO implements Serializable {
    /** 条目id */
    private Long id;
    /** 条目名称 */
    private String name;

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
}

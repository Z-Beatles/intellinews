package com.fintech.intellinews.vo;

import java.io.Serializable;

/**
 * @author waynechu
 * Created 2017-11-24 09:16
 */
public class HotKeywordVO implements Serializable {
    /** 关键字id */
    private Long id;
    /** 关键字名称 */
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

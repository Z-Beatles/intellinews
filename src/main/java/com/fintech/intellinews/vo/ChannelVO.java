package com.fintech.intellinews.vo;

import java.io.Serializable;

/**
 * @author waynechu
 * Created 2017-11-10 09:37
 */
public class ChannelVO implements Serializable{
    /** 频道id */
    private Long id;
    /** 频道英文名 */
    private String name;
    /** 频道中文名 */
    private String nameZh;

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

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }
}

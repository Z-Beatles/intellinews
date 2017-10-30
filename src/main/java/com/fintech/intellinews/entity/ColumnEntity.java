package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

/**
 * @author waynechu
 * Created 2017-10-27 13:56
 */
public class ColumnEntity extends BaseEntity {
    private Long id;

    private String name;

    private String nameZh;

    private String depict;

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

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }
}

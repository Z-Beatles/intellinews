package com.fintech.intellinews.vo;

/**
 * @author wanghao
 * create 2017-11-13 16:13
 **/
public class UserSectionVO {
    private Long id;
    private String name;
    private String logo;
    private String date;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

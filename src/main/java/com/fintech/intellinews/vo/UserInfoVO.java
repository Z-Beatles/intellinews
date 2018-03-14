package com.fintech.intellinews.vo;

import java.io.Serializable;

/**
 * @author waynechu
 * Created 2017-11-09 17:33
 */
public class UserInfoVO implements Serializable {
    /** 用户id */
    private Long id;
    /** 用户头像 */
    private String avatar;
    /** 用户昵称 */
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

public class UserLoginEntity extends BaseEntity {
    private Long id;

    private String avatar;

    private String username;

    private String nickname;

    private String passwordHash;

    private String passwordSalt;

    private String passwordAlgo;

    private Integer passwordIteration;

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
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash == null ? null : passwordHash.trim();
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
    }

    public String getPasswordAlgo() {
        return passwordAlgo;
    }

    public void setPasswordAlgo(String passwordAlgo) {
        this.passwordAlgo = passwordAlgo == null ? null : passwordAlgo.trim();
    }

    public Integer getPasswordIteration() {
        return passwordIteration;
    }

    public void setPasswordIteration(Integer passwordIteration) {
        this.passwordIteration = passwordIteration;
    }
}
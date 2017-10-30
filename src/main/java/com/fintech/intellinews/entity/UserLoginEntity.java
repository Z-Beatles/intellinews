package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

public class UserLoginEntity extends BaseEntity {
    private Long id;

    private String username;

    private String avatar;

    private String passwordHash;

    private String passwordSalt;

    private String passwordAlgo;

    private Integer passwordIteration;

    private Boolean isDisabled;

    private Boolean isLocked;

    public UserLoginEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
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

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
}
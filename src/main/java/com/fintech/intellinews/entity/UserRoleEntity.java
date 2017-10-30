package com.fintech.intellinews.entity;

import com.fintech.intellinews.base.BaseEntity;

public class UserRoleEntity extends BaseEntity {
    private Long id;

    private Long loginUserId;

    private Long roleId;

    public UserRoleEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
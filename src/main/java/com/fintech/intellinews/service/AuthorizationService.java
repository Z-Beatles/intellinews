package com.fintech.intellinews.service;

import com.fintech.intellinews.dao.RoleDao;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-23 14:04
 */
@Service
public class AuthorizationService extends BaseService {

    private RoleDao roleDao;

    private PermissionDao permissionDao;

    public List<String> getRoles(Long loginUserId) {
        return roleDao.listByLoginUserId(loginUserId);
    }

    public List<String> getPermissions(String role) {
        return permissionDao.listByRoleName(role);
    }


    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }
}

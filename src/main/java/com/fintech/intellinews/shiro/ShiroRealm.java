package com.fintech.intellinews.shiro;

import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.service.AuthorizationService;
import com.fintech.intellinews.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-23 14:04
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    private AuthorizationService authorizationService;

    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserLoginEntity principal = (UserLoginEntity) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> roles = authorizationService.getRoles(principal.getId());
        List<String> permissions = new ArrayList<>();
        for (String role : roles) {
            permissions.addAll(authorizationService.getPermissions(role));
        }
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LoginAuthenticationToken myToken = (LoginAuthenticationToken) token;
        UserLoginEntity userLoginEntity = userService.getByAccount(myToken.getUsername());

        if (userLoginEntity == null) {
            throw new UnknownAccountException("账号[" + myToken.getLoginType() + "," + myToken.getUsername() + "]不存在");
        } else if (Boolean.TRUE.equals(userLoginEntity.getIsLocked())) {
            throw new LockedAccountException("帐号[" + myToken.getLoginType() + "," + myToken.getUsername() + "]已锁定");
        } else if (Boolean.TRUE.equals(userLoginEntity.getIsDisabled())) {
            throw new DisabledAccountException("帐号[" + myToken.getLoginType() + "," + myToken.getUsername() + "]无效");
        }
        return new SimpleAuthenticationInfo(userLoginEntity, userLoginEntity.getPasswordHash(), ByteSource.Util.bytes
                (userLoginEntity.getPasswordSalt()), getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof LoginAuthenticationToken || super.supports(token);
    }


    @Autowired
    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}

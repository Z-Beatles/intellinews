package com.fintech.intellinews.shiro;

import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author waynechu
 * Created 2017-10-23 14:04
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LoginAuthenticationToken myToken = (LoginAuthenticationToken) token;
        UserLoginEntity userLoginEntity = userService.getByAccount(myToken.getUsername());

        if (userLoginEntity == null) {
            throw new UnknownAccountException("账号[" + myToken.getLoginType() + "," + myToken.getUsername() + "]不存在");
        }
        return new SimpleAuthenticationInfo(userLoginEntity, userLoginEntity.getPasswordHash(), ByteSource.Util.bytes
                (userLoginEntity.getPasswordSalt()), getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof LoginAuthenticationToken || super.supports(token);
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}

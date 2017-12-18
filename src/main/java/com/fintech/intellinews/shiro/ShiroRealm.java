package com.fintech.intellinews.shiro;

import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.properties.ShiroProperties;
import com.fintech.intellinews.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author waynechu
 * Created 2017-10-23 14:04
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private ShiroProperties shiroProperties;

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        LoginAuthenticationToken myToken = (LoginAuthenticationToken) token;
        UserLoginEntity userLoginEntity = userService.getByAccount(myToken.getUsername());

        if (userLoginEntity == null) {
            throw new UnknownAccountException("账号[" + myToken.getLoginType() + "," + myToken.getUsername() + "]不存在");
        }
        return new SimpleAuthenticationInfo(userLoginEntity, userLoginEntity.getPasswordHash(), ByteSource.Util.bytes
                (userLoginEntity.getPasswordSalt()), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(shiroProperties.getAlgorithmName());
        matcher.setHashIterations(shiroProperties.getHashIterations());
        this.setCredentialsMatcher(matcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof LoginAuthenticationToken || super.supports(token);
    }
}

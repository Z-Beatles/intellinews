package com.fintech.intellinews.service.impl;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.SessionService;
import com.fintech.intellinews.shiro.LoginAuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author waynechu
 * Created 2017-11-07 13:14
 */
@Service
public class SessionServiceImpl implements SessionService {

    private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    /**
     * 用户登录
     *
     * @param loginType   登录类型
     * @param account     帐号
     * @param password    密码
     * @param rememberMe  记住我
     * @param host        IP地址
     * @param currentUser 用户
     * @return 用户id
     */
    @Override
    public Long doLogin(String loginType, String account, String password, boolean rememberMe, String host,
                        Subject currentUser) {
        LoginAuthenticationToken token = new LoginAuthenticationToken(loginType, account, password, rememberMe,
                host);
        token.setRememberMe(rememberMe);
        try {
            currentUser.login(token);
            UserLoginEntity principal = (UserLoginEntity) currentUser.getPrincipal();
            logger.info("账号{}登陆成功", account);
            return principal.getId();
        } catch (UnknownAccountException e) {
            logger.warn("账号{}不存在", account);
            throw new AppException(ResultEnum.ACCOUNT_NOT_EXIST_ERROR);
        } catch (IncorrectCredentialsException e) {
            logger.warn("密码错误，账号：{}", account);
            throw new AppException(ResultEnum.WRONG_PASSWORD_ERROR);
        } catch (AuthenticationException e) {
            logger.warn("登录失败", e);
            throw new AppException(ResultEnum.LOGIN_FAILED_ERROR);
        } catch (Exception e) {
            logger.error("系统异常", e);
        }
        return null;
    }

    /**
     * 用户退出
     *
     * @return 用户id
     */
    @Override
    public Long doLogout() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            UserLoginEntity principal = (UserLoginEntity) currentUser.getPrincipal();
            String account = principal.getUsername();
            try {
                currentUser.logout();
                logger.info("用户{}退出系统", account);
                return principal.getId();
            } catch (Exception e) {
                logger.error("系统异常", e);
            }
        }
        throw new AppException(ResultEnum.NOT_LOGIN_ERROR);
    }
}

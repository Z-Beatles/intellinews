package com.fintech.intellinews.service;

import org.apache.shiro.subject.Subject;

/**
 * @author waynechu
 * Created 2017-11-07 13:14
 */
public interface SessionService {

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
    Long doLogin(String loginType, String account, String password,
                 boolean rememberMe, String host, Subject currentUser);

    /**
     * 用户退出
     *
     * @return 用户id
     */
    Long doLogout();
}

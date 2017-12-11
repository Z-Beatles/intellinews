package com.fintech.intellinews.config;

import com.fintech.intellinews.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author wanghao
 * create 2017-12-02 23:31
 **/
public class ShiroConfig {

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }

    @Bean
    public SimpleCookie sessionCookieId() {
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(31536000);
        return simpleCookie;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(SimpleCookie sessionCookieId) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(1800000);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookie(sessionCookieId);
        return sessionManager;
    }

    @Bean
    public ModularRealmAuthenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }

    @Bean
    public Realm realm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("SHA-1");
        matcher.setHashIterations(1024);
        shiroRealm.setCredentialsMatcher(matcher);
        return shiroRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(
            Realm realm,
            DefaultWebSessionManager sessionManager,
            EhCacheManager ehCacheManager,
            ModularRealmAuthenticator authenticator) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setCacheManager(ehCacheManager);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setAuthenticator(authenticator);
        List<Realm> list = new ArrayList<>();
        list.add(realm);
        defaultWebSecurityManager.setRealms(list);
        return defaultWebSecurityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(new Object[]{securityManager});
        return methodInvokingFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/v1/sessions");
        HashMap<String, String> map = new HashMap<>();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

}

package com.fintech.intellinews.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author wanghao
 * create 2017-12-02 23:31
 **/
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroEhcacheManager")
    public EhCacheManagerFactoryBean shiroEhcacheManager(ApplicationContext applicationContext) {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setCacheManagerName("shiroEhcacheManager");
        factory.setConfigLocation(applicationContext.getResource("classpath:META-INF/ehcache-shiro.xml"));
        return factory;
    }

    @Bean(name = "sessionDAO")
    public EnterpriseCacheSessionDAO sessionDAO(
            @Qualifier("shiroCacheManagerForEhcache") CacheManager shiroCacheManagerForEhcache) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setCacheManager(shiroCacheManagerForEhcache);
        return sessionDAO;
    }

    @Bean(name = "shiroCacheManagerForEhcache")
    public EhCacheManager shiroCacheManagerForEhcache(
            @Qualifier("shiroEhcacheManager") net.sf.ehcache.CacheManager shiroEhcacheManager) {
        EhCacheManager shiroCacheManagerForEhcache = new EhCacheManager();
        shiroCacheManagerForEhcache.setCacheManager(shiroEhcacheManager);
        return shiroCacheManagerForEhcache;
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(31536000);
        return simpleCookie;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(
            @Qualifier("sessionDAO") SessionDAO sessionDAO,
            @Qualifier("sessionIdCookie") SimpleCookie sessionIdCookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(1800000);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(
            Realm realm,
            @Qualifier("sessionManager") SessionManager sessionManager,
            @Qualifier("shiroCacheManagerForEhcache") CacheManager shiroCacheManagerForEhcache) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        defaultWebSecurityManager.setCacheManager(shiroCacheManagerForEhcache);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        return defaultWebSecurityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/v1/sessions");
        HashMap<String, String> map = new HashMap<>();

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}

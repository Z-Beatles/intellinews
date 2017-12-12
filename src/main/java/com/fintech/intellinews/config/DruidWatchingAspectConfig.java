package com.fintech.intellinews.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wanghao
 * create 2017-12-12 9:51
 **/
@Aspect
public class DruidWatchingAspectConfig {

    @Bean
    public DruidStatInterceptor interceptor() {
        return new DruidStatInterceptor();
    }

    @Bean
    public JdkRegexpMethodPointcut methodPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String dao = "com.fintech.intellinews.dao.*";
        String service = "com.fintech.intellinews.service.*";
        druidStatPointcut.setPatterns(dao, service);
        return druidStatPointcut;
    }

    @Bean
    public Advisor druidStatAdvisor(JdkRegexpMethodPointcut methodPointcut,DruidStatInterceptor interceptor) {
        return new DefaultPointcutAdvisor(methodPointcut, interceptor);
    }
}

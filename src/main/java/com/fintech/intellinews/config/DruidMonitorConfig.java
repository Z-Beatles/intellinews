package com.fintech.intellinews.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * @author wanghao
 * create 2017-12-12 9:51
 **/
public class DruidMonitorConfig {

    @Bean("druid-stat-interceptor")
    public DruidStatInterceptor interceptor() {
        return new DruidStatInterceptor();
    }

    @Bean("druid-stat-pointcut")
    public JdkRegexpMethodPointcut methodPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String dao = "com.fintech.intellinews.dao.*";
        String service = "com.fintech.intellinews.service.*";
        druidStatPointcut.setPatterns(dao, service);
        return druidStatPointcut;
    }

    @Bean
    public Advisor druidStatAdvisor(@Qualifier("druid-stat-pointcut") JdkRegexpMethodPointcut methodPointcut,
                                    @Qualifier("druid-stat-interceptor") DruidStatInterceptor interceptor) {
        return new DefaultPointcutAdvisor(methodPointcut, interceptor);
    }
}

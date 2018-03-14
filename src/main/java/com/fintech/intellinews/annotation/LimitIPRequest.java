package com.fintech.intellinews.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 用于IP访问控制
 * 可添加黑名单、白名单策略
 *
 * @author waynechu
 * Created 2018-03-14 17:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface LimitIPRequest {

    /**
     * 限制某时间段内可访问次数，默认5次
     */
    int limitCounts() default 5;

    /**
     * 限制访问的间段，默认为60秒
     */
    int timeSecond() default 60;

    /**
     * IP白名单，跳过访问次数检查
     */
    String[] whiteList() default {"172.17.0.1"};

    /**
     * IP黑名单，无接口访问权限
     */
    String[] blackList() default {};
}

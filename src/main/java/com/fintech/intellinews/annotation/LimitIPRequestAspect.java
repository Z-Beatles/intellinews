package com.fintech.intellinews.annotation;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.enums.ResultEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author waynechu
 * Created 2018-03-14 17:21
 */
@Aspect
@Component
public class LimitIPRequestAspect {
    private static final Logger logger = LoggerFactory.getLogger(LimitIPRequestAspect.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("execution(* com.fintech.intellinews.web.*.*(..)) && @annotation(com.fintech.intellinews.annotation.LimitIPRequest)")
    public void before() {
    }

    @Before("before()")
    public void requestLimit(JoinPoint joinPoint) {
        // 获取HttpRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        LimitIPRequest limitIPRequest = getAnnotation(joinPoint);
        if (limitIPRequest == null) {
            return;
        }

        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI().toLowerCase();
        // 黑名单检测
        for (String whiteIP : limitIPRequest.blackList()) {
            if (whiteIP.equals(ip)) {
                logger.debug("【BLACK_IP_REQUEST】,url{},ip{}", uri, ip);
                throw new AppException(ResultEnum.BLACK_IP_REQUEST_ERROR);
            }
        }
        // 白名单检测
        for (String whiteIP : limitIPRequest.whiteList()) {
            if (whiteIP.equals(ip)) {
                return;
            }
        }
        String redisKey = "ip-request-count:" + uri + ":" + ip;
        // 在redis自增，步长为1
        long count = redisTemplate.opsForValue().increment(redisKey, 1);
        // 从第一次访问开始计时
        if (count == 1) {
            redisTemplate.expire(redisKey, limitIPRequest.timeSecond(), TimeUnit.SECONDS);
        }

        if (count > limitIPRequest.limitCounts()) {
            logger.debug("【FREQUENTLY_IP_REQUEST】,url{},ip{}", uri, ip);
            throw new AppException(ResultEnum.FREQUENTLY_IP_REQUEST_ERROR);
        }
    }

    /**
     * 获得注解
     *
     * @param joinPoint 切入点
     * @return 注解
     */
    private LimitIPRequest getAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(LimitIPRequest.class);
        }
        return null;
    }
}

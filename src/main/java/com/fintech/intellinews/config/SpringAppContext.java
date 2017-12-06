package com.fintech.intellinews.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author wanghao
 * create 2017-12-02 23:07
 **/
@EnableTransactionManagement
@Configuration
@ComponentScan(
        basePackages = {"com.fintech.intellinews"},
        excludeFilters = {@ComponentScan.Filter(
                value = {
                        EnableWebMvc.class,
                        ControllerAdvice.class,
                        Controller.class
                }
        )}
)
@Import({ShiroConfig.class, RedisConfig.class})
public class SpringAppContext {

    @Configuration
    @Profile("product")
    @PropertySource("classpath:application.product.properties")
    static class Production {
    }

    @Configuration
    @Profile("develop")
    @PropertySource("classpath:application.develop.properties")
    static class Development {
    }
}

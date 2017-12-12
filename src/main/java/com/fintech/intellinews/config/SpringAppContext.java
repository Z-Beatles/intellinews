package com.fintech.intellinews.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
                        Controller.class
                }
        )}
)
@Import({ShiroConfig.class, RedisConfig.class,DruidWatchingAspectConfig.class})
@PropertySource("classpath:application.properties")
public class SpringAppContext {

}

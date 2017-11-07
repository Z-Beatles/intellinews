package com.fintech.intellinews.service;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.UserConfigEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author wanghao
 * create 2017-11-07 13:20
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
@ActiveProfiles("develop")
public class ChannelServiceTest {

    @Autowired
    private UserConfigService userConfigService;

    @Test
    public void configServiceTest(){
        UserConfigEntity userConfigEntity =
                userConfigService.getUserConfig(1L);
        System.out.println(userConfigEntity);
    }

}

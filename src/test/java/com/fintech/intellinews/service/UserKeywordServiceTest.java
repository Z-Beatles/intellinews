package com.fintech.intellinews.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author wanghao
 * create 2017-11-30 9:32
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
@ActiveProfiles("develop")
public class UserKeywordServiceTest {

    @Autowired
    private UserKeywordService userKeywordService;

    @Test
    public void testKeyword(){
        userKeywordService.updateHobbyAttention(6L,"阿里巴巴1");
    }

}

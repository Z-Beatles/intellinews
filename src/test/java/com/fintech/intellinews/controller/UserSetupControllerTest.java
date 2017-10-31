package com.fintech.intellinews.controller;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.UserSetupEntity;
import com.fintech.intellinews.service.UserSetupService;
import com.fintech.intellinews.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author wanghao
 * create 2017-10-31 9:26
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/spring-context.xml"})
@ActiveProfiles("develop")
public class UserSetupControllerTest {

    @Autowired
    private UserSetupService userSetupService;

    @Test
    public void currentUserSetup(){
        Result<UserSetupEntity> result = userSetupService.getCurrentUserSetup(1L);
        System.out.println(result.getMessage());
        System.out.println(result.getData());
    }

}

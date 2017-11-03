package com.fintech.intellinews.controller;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.AdvertiseEntity;
import com.fintech.intellinews.web.AdvertiseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author wanghao
 * create 2017-10-31 12:32
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/spring-context.xml"})
@ActiveProfiles("develop")
public class AdvertiseControllerTest {

    @Autowired
    private AdvertiseController advertiseController;

    @Test
    public void testAdsActive(){
        Result<List<AdvertiseEntity>> result = advertiseController.listActiveAds();
        System.out.println(result.getData().size());
    }

}

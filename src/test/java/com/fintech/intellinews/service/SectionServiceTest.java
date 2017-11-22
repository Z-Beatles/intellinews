package com.fintech.intellinews.service;

import com.fintech.intellinews.util.ResultUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author waynechu
 * Created 2017-11-22 13:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
@ActiveProfiles("develop")
public class SectionServiceTest {
    @Autowired
    private SectionService sectionService;

    @Test
    public void listAtlasBySectionId() throws Exception {
        System.out.println(ResultUtil.success(sectionService.listAtlasBySectionId(1L)));
    }

}
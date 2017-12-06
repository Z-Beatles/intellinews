package com.fintech.intellinews;

import com.fintech.intellinews.config.SpringAppContext;
import com.fintech.intellinews.config.SpringMvcContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author wanghao
 * create 2017-11-30 12:45
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringAppContext.class
        , SpringMvcContext.class})
@ActiveProfiles("develop")
public class BaseTest {

    public final static String API_VERSION = "/v1";

    @Autowired
    protected WebApplicationContext ctx;

    @Autowired
    private DefaultWebSecurityManager securityManager;

    protected MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        SecurityUtils.setSecurityManager(securityManager);
    }

}

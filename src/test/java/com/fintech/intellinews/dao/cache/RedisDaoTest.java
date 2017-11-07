package com.fintech.intellinews.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author waynechu
 * Created 2017-10-20 16:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
@ActiveProfiles("develop")
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;

    @Test
    public void get() throws Exception {
        redisDao.get("accessToken");
    }

    @Test
    public void set() throws Exception {
        byte[] bytes = new byte[]{1, 2, 3};
        redisDao.set("accessToken", bytes);
    }

    @Test
    public void setEx() throws Exception {

    }

    @Test
    public void del() throws Exception {
    }

}
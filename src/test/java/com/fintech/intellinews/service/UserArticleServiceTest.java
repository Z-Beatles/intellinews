package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.UserCommentVO;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * create 2017-11-11 20:44
 **/
public class UserArticleServiceTest {

    @Autowired
    private UserArticleService userArticleService;

    @Autowired
    private UserService userService;

    @Test
    public void testInsert(){
//        PageInfo<UserCommentVO> result = userService.getUserComments(22L,1,10);
//        System.out.println(result);

        Map<Long,String> map = new HashMap<>();
        Long a = new Long("1222");
        map.put(1222L,"1");
        System.out.println(map.get(a));
    }

}

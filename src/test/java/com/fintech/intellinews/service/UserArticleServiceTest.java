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
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
@ActiveProfiles("develop")
public class UserArticleServiceTest {

    @Autowired
    private UserArticleService userArticleService;

    @Autowired
    private UserService userService;

    @Test
    public void testInsert(){
//        PageInfo<UserCommentVO> result = userService.getUserComments(22L,1,10);
//        System.out.println(result.getList().size());
        userArticleService.insertUserArticle(27L,1L);
//        PageInfo pageInfo = userArticleService.getUserCollectArticles(1L,1,10);
//        System.out.println(pageInfo.getList().size());
    }

}

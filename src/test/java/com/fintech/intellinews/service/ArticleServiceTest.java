package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.DetailsArticleVO;
import com.fintech.intellinews.vo.SearchArticleVO;
import com.github.pagehelper.PageInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-10 11:15
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
@ActiveProfiles("develop")
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;
    private Long start = 0L;

    @Before
    public void beforeTest(){
        start = System.currentTimeMillis();
    }
    @Test
    public void testSearch(){
        PageInfo<SearchArticleVO> pageInfo = articleService.getArticlesByKeyword("中",1,10);
        System.out.println(pageInfo);
    }

    public void testDetails(){
        DetailsArticleVO detailsArticleVO = articleService.getDetailsArticleById(16L);
        System.out.println(detailsArticleVO);
    }


    @After
    public void afterTest(){
        Long end = System.currentTimeMillis();
        System.out.println((end - start) +"毫秒");
    }

}

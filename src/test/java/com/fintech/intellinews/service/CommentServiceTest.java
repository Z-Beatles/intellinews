package com.fintech.intellinews.service;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.vo.CommentVO;
import com.fintech.intellinews.vo.DetailsArticleVO;
import com.fintech.intellinews.vo.SearchArticleVO;
import com.fintech.intellinews.web.ArticleController;
import com.fintech.intellinews.web.CommentController;
import com.github.pagehelper.Page;
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

import java.util.List;

/**
 * @author wanghao
 * create 2017-11-10 17:39
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
@ActiveProfiles("develop")
public class CommentServiceTest {


    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleController articleController;

    private Long start = 0L;

    @Before
    public void beforeTest(){
        start = System.currentTimeMillis();
    }

    @Test
    public void testComments(){
        Result<PageInfo<CommentVO>> result = articleController.getArticleComments(1L,1,3);
//        PageInfo<CommentVO>  pageInfo = commentService.listArticleComments(1L,1,3);
        System.out.println(result);
    }

    @After
    public void afterTest(){
        Long end = System.currentTimeMillis();
        System.out.println((end - start) +"毫秒");
    }

}

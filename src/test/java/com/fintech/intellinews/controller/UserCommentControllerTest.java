package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-12-01 13:36
 **/
public class UserCommentControllerTest extends BaseTest {

    /**
     * 获取用户所有评论
     */

    @Before
    public void login() throws Exception {
        mockMvc.perform(post(API_VERSION+"/sessions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("account","666666")
                .param("password","666666"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1007"));
    }

    @Test
    public void testGetCurrentUserAllComments() throws Exception {
        mockMvc.perform(get(API_VERSION+"/users/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 用户评论文章
     */
    @Test
    public void testUserCommentArticle() throws Exception {
        mockMvc.perform(post(API_VERSION+"/users/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("单元测试发表文章评论")
                .param("articleId","1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0"));
    }

}

package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-12-01 13:03
 **/
public class UserArticleControllerTest extends BaseTest {
    /**
     * 用户登录
     * @throws Exception
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

    /**
     * 获取用户所有收藏文章
     * @throws Exception
     */
    @Test
    public void testGetAllUserArticlesSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/users/articles")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("0"))
            .andReturn().getResponse().getContentAsString();
    }

    /**
     * 通过文章id获取当前用户收藏的指定文章
     */
    @Test
    public void testGetCurrentUserArticleById() throws Exception {
        mockMvc.perform(get(API_VERSION+"/users/articles/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("0"))
            .andReturn().getResponse().getContentAsString();
    }

    /**
     * 用户收藏文章成功
     */
    @Test
    public void testUserCollectArticleSuccess() throws Exception {
        mockMvc.perform(post(API_VERSION+"/users/articles/6")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.code").value("0"))
        .andReturn().getResponse().getContentAsString();
    }

    /**
     * 用户取消收藏文章成功
     */
    @Test
    public void testUserCancelCollectArticleSuccess() throws Exception {
        mockMvc.perform(delete(API_VERSION+"/users/articles/6")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0"))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * 用户收藏文章失败
     */
    @Test
    public void testUserCollectArticleFailed() throws Exception {
        mockMvc.perform(post(API_VERSION+"/users/articles/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("10004"))
            .andReturn().getResponse().getContentAsString();
    }

}

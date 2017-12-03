package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-12-01 10:27
 **/
public class ArticleControllerTest extends BaseTest {

    /**
     * 根据id成功获取文章
     * @throws Exception
     */
    @Test
    public void testGetArticleByIdSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/articles/1").contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0"))
            .andExpect(jsonPath("$.data.id").value("1"));
    }

    /**
     * 根据id获取不存在的文章资源
     */
    @Test
    public void testGetNotExistArticleByIdSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/articles/-1").contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("-1"));
    }

    /**
     * 根据文章id成功获取用户评论
     */
    @Test
    public void testGetCommentsByArticleIdSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/articles/1/comments").contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 根据搜索关键字成功获取文章列表
     */
    @Test
    public void testSearchArticleByKeywordsSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/articles/search")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .param("keyword","中国"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0"));
    }
}

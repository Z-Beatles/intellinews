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
 * create 2017-12-01 14:20
 **/
public class UserSectionControllerTest extends BaseTest {

    @Before
    public void login() throws Exception {
        mockMvc.perform(post(API_VERSION + "/sessions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("account", "666666")
                .param("password", "666666"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1007"));
    }

    /**
     * 获取当前用户收藏的所有条目
     *
     * @throws Exception 异常
     */
    @Test
    public void testGetAllCurrentUserSection() throws Exception {
        mockMvc.perform(get(API_VERSION + "/users/sections")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 获取当前用户收藏的指定条目
     *
     * @throws Exception 异常
     */
    @Test
    public void testUserSectionById() throws Exception {
        mockMvc.perform(get(API_VERSION + "/users/sections/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 用户收藏条目
     *
     * @throws Exception 异常
     */
    @Test
    public void testUserCollectSection() throws Exception {
        mockMvc.perform(post(API_VERSION + "/users/sections/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 用户收藏条目
     *
     * @throws Exception 异常
     */
    @Test
    public void testUserCancelCollectSection() throws Exception {
        mockMvc.perform(delete(API_VERSION + "/users/sections/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0"));
    }

}

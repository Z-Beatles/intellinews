package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-12-01 14:16
 **/
public class UserKeywordControllerTest extends BaseTest {


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
     * 获取当前用户的所有偏好关键字
     * @throws Exception 异常
     */
    @Test
    public void testGetCurrentUserKeyword() throws Exception {
        mockMvc.perform(get(API_VERSION+"/users/keywords")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("0"));
    }
    /**
     * 添加用户偏好关键字
     * @throws Exception 异常
     */
    @Test
    public void testAddCurrentUserKeyword() throws Exception {
        mockMvc.perform(get(API_VERSION+"/users/keywords")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("keyword","单元测试添加用户偏好"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("0"));
    }


}

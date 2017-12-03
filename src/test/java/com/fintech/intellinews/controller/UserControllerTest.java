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
 * create 2017-12-01 13:58
 **/
public class UserControllerTest extends BaseTest {


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
     * 获取当前用户的个人信息
     * @throws Exception 异常
     */
    @Test
    public void testGetCurrentUserInfo() throws Exception {
        mockMvc.perform(get(API_VERSION+"/users/me")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 用户注册
     * @throws Exception 异常
     */
    @Test
    public void testRegisterUser() throws Exception {
        mockMvc.perform(post(API_VERSION+"/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("username","test")
                .param("password","test"))
                .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("1009"));
    }

}

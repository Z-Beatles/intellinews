package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.apache.shiro.SecurityUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-12-01 11:45
 **/
public class SessionControllerTest extends BaseTest {

    /**
     * 用户登录成功
     */
    @Test
    public void testUserLoginSuccess() throws Exception {
        mockMvc.perform(post(API_VERSION+"/sessions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("account","666666")
                .param("password","666666"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("1007"));
    }

    /**
     * 重复登录
     * @throws Exception
     */
    @Test
    public void testRepeatLogin() throws Exception {
        testUserLoginSuccess();
        mockMvc.perform(post(API_VERSION+"/sessions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("account","666666")
                .param("password","666666"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value("1005"));
    }

    /**
     * 账户不存在
     */
    @Test
    public void testNoUserAccount() throws Exception {
        mockMvc.perform(post(API_VERSION+"/sessions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("account","NoneAccount")
                .param("password","666666"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("1000"));
    }


    /**
     * 用户尚未登录
     */
    @Test
    public void testNoUserLogin() throws Exception {
        mockMvc.perform(delete(API_VERSION+"/sessions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("account","666666")
                .param("password","666666"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value("1006"));
    }

    /**
     * 注销账户
     */
    @Test
    public void testUserLogout() throws Exception {
        testUserLoginSuccess();
        mockMvc.perform(delete(API_VERSION+"/sessions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("account","666666")
                .param("password","666666"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("1008"));
    }

}

package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-12-01 13:47
 **/
public class UserConfigControllerTest extends BaseTest {

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
     * 获取当前用户的频道配置
     * @throws Exception 异常
     */
    @Test
    public void testGetCurrentUserAllConfigs() throws Exception {
        mockMvc.perform(get(API_VERSION+"/users/channels")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 更新当前用户的频道配置
     */
    @Test
    public void testUpdateUserChannels() throws Exception {
        mockMvc.perform(put(API_VERSION+"/users/channels")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("[{\"id\": 1, \"name\": \"latest\", \"nameZh\": \"最新\"}, {\"id\": 22, \"name\": \"cba\", \"nameZh\": \"CBA\"}, {\"id\": 8, \"name\": \"technology\", \"nameZh\": \"科技\"}, {\"id\": 3, \"name\": \"industry\", \"nameZh\": \"行业\"}, {\"id\": 9, \"name\": \"workplace\", \"nameZh\": \"职场\"}, {\"id\": 4, \"name\": \"banking\", \"nameZh\": \"金融\"}, {\"id\": 5, \"name\": \"sports\", \"nameZh\": \"体育\"}, {\"id\": 2, \"name\": \"policy\", \"nameZh\": \"政策\"}]"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0"));
    }

}

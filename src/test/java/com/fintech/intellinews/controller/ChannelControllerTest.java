package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-12-01 10:59
 **/
public class ChannelControllerTest extends BaseTest {

    /**
     * 据频道id成功获取频道下的文章列表
     */
    @Test
    public void testGetArticlesByChannelIdSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/channels/1/articles")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0"));
    }

}

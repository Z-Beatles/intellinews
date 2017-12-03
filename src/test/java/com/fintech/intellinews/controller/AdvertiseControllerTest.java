package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-11-30 15:23
 **/
public class AdvertiseControllerTest extends BaseTest {

    /**
     * 上架广告测试
     * @throws Exception 异常
     */
    @Test
    public void testActiveAdvertise() throws Exception {
        mockMvc.perform(get(API_VERSION+"/advertise")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0"))
            .andReturn().getResponse().getContentAsString();
    }

}

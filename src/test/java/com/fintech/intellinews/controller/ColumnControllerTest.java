package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-12-01 11:03
 **/
public class ColumnControllerTest extends BaseTest {

    /**
     * 成功获取所有专栏列表
     */
    @Test
    public void testGetAllColumnSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/columns")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0"));
    }

}

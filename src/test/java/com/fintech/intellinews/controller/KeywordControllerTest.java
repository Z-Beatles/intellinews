package com.fintech.intellinews.controller;

import com.fintech.intellinews.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wanghao
 * create 2017-12-01 11:15
 **/
public class KeywordControllerTest extends BaseTest {

    /**
     * 成功获取热门搜索关键字
     */
    @Test
    public void testHotKeywordArticlesSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/keywords")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("0"));
    }

}

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
 * create 2017-12-01 11:35
 **/
public class SectionControllerTest extends BaseTest {
    /**
     * 成功获取条目列表
     * @throws Exception
     */
    @Test
    public void testGetSectionsSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/sections")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 根据条目id成功获取条目列表
     * @throws Exception
     */
    @Test
    public void testGetSectionsByIdSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/sections/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 根据关键字成功获取条目列表
     * @throws Exception
     */
    @Test
    public void testGetSectionsByKeywordSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/sections/search")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("keyword","小米"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("0"));
    }

    /**
     * 根据条目id成功查询图谱信息
     */
    @Test
    public void testGetAliasBySectionIdSuccess() throws Exception {
        mockMvc.perform(get(API_VERSION+"/sections/1/atlas")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.code").value("0"));
    }

}

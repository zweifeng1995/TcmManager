/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.TCMBrain.controller;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-21
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MedicineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/rest/gucheng/tcmbrain/v1/medicines")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\": \"中药名\"," +
                                    "\"mmp_research\": \"现代医学药理研究\", " +
                                    "\"properties\":[{\"kind\": \"hot\", \"level\":5}, {\"kind\": \"rise\", \"level\":6}]," +
                                    "\"nature_and_flavor\":\"nature_and_flavor\"," +
                                    "\"channel_tropism\": \"channel_tropism\"," +
                                    "\"efficacy\":\"efficacy\"," +
                                    "\"clinical_application\":\"clinical_application\"}"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            log.error("", e);
        }
    }
}

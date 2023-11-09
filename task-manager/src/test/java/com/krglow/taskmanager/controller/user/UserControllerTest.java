package com.krglow.taskmanager.controller.user;

import static com.krglow.taskmanager.JsonUtil.serialize;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.EMAIL_VALUE;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.ID_VALUE;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.ITEM;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.NAME_VALUE;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.SAMPLE_EMAIL;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.SAMPLE_ID;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.SAMPLE_NAME;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.SAMPLE_SURNAME;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.SURNAME_VALUE;
import static com.krglow.taskmanager.dataset.user.SqlUserInit.USER;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krglow.taskmanager.service.user.dto.UserDto;


@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsers() throws Exception {
        MvcResult result = mockMvc.perform(get(USER))
                .andExpect(status().isOk())
                .andExpect(jsonPath(ITEM + SAMPLE_ID).value(ID_VALUE))
                .andExpect(jsonPath(ITEM + SAMPLE_NAME).value(NAME_VALUE))
                .andExpect(jsonPath(ITEM + SAMPLE_SURNAME).value(SURNAME_VALUE))
                .andExpect(jsonPath(ITEM + SAMPLE_EMAIL).value(EMAIL_VALUE))
                .andReturn();
        assertNotNull(result);
    }

    @Test
    void read() throws Exception {
        MvcResult result = mockMvc.perform(get(USER + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(SAMPLE_ID).value(ID_VALUE))
                .andExpect(jsonPath(SAMPLE_NAME).value(NAME_VALUE))
                .andExpect(jsonPath(SAMPLE_SURNAME).value(SURNAME_VALUE))
                .andExpect(jsonPath(SAMPLE_EMAIL).value(EMAIL_VALUE))
                .andReturn();
        assertNotNull(result);
    }

    @Test
    void createTest() throws Exception {

        UserDto dto = sampleCreateUser();

        MvcResult result = mockMvc.perform(post(USER).content(serialize(dto, objectMapper)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertNotNull(result);
    }

    @Test
    void updateTest() throws Exception {

        UserDto dto = sampleCreateUser();

        MvcResult result = mockMvc.perform(put(USER + "/3").content(serialize(dto, objectMapper)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertNotNull(result);
    }

    @Test
    void deleteUser() throws Exception {
        MvcResult result = mockMvc.perform(delete(USER + "/2")).andExpect(status().isOk()).andReturn();
        assertNotNull(result);
    }

    private UserDto sampleCreateUser() {
        UserDto result = new UserDto();
        result.setEmail("kgkg@kg.pl");
        result.setName("hahaha");
        result.setSurname("xdxdxd");
        return result;
    }

}

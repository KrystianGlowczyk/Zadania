package com.krglow.taskmanager.controller.task;

import static com.krglow.taskmanager.dataset.task.SqlTaskInit.ASSIGNEDUSERS_VALUE;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.DESCRIPTION_VALUE;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.EXECUTETIME_VALUE;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.ID_2;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.ID_VALUE;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.ITEM;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.SAMPLE_ASSIGNEDUSERS;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.SAMPLE_DESCRIPTION;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.SAMPLE_EXECUTETIME;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.SAMPLE_ID;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.SAMPLE_STATUSID;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.SAMPLE_STATUSNAME;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.SAMPLE_TITLE;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.STATUSID_VALUE;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.STATUSNAME_VALUE;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.TASK;
import static com.krglow.taskmanager.dataset.task.SqlTaskInit.TITLE_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krglow.taskmanager.entity.task.TaskEntity;
import com.krglow.taskmanager.repository.task.TaskRepository;
import com.krglow.taskmanager.service.task.dto.TaskDto;


@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private TaskRepository taskRepository;

    @Test
    void getAllUsers() throws Exception {
        MvcResult result = mockMvc.perform(get(TASK))
                .andExpect(status().isOk())
                .andExpect(jsonPath(ITEM + SAMPLE_ID).value(ID_VALUE))
                .andExpect(jsonPath(ITEM + SAMPLE_TITLE).value(TITLE_VALUE))
                .andExpect(jsonPath(ITEM + SAMPLE_DESCRIPTION).value(DESCRIPTION_VALUE))
                .andExpect(jsonPath(ITEM + SAMPLE_STATUSID).value(STATUSID_VALUE))
                .andExpect(jsonPath(ITEM + SAMPLE_STATUSNAME).value(STATUSNAME_VALUE))
                .andExpect(jsonPath(ITEM + SAMPLE_EXECUTETIME).value(EXECUTETIME_VALUE))
                .andExpect(jsonPath(ITEM + SAMPLE_ASSIGNEDUSERS).value(ASSIGNEDUSERS_VALUE))
                .andReturn();
        assertNotNull(result);
    }

    @Test
    void read() throws Exception {
        MvcResult result = mockMvc.perform(get(TASK + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(SAMPLE_ID).value(ID_VALUE))
                .andExpect(jsonPath(SAMPLE_TITLE).value(TITLE_VALUE))
                .andExpect(jsonPath(SAMPLE_DESCRIPTION).value(DESCRIPTION_VALUE))
                .andExpect(jsonPath(SAMPLE_STATUSID).value(STATUSID_VALUE))
                .andExpect(jsonPath(SAMPLE_STATUSNAME).value(STATUSNAME_VALUE))
                .andExpect(jsonPath(SAMPLE_EXECUTETIME).value(EXECUTETIME_VALUE))
                .andExpect(jsonPath(SAMPLE_ASSIGNEDUSERS).value(ASSIGNEDUSERS_VALUE))
                .andReturn();
        assertNotNull(result);
    }

    @Test
    void createTest() throws Exception {

        TaskDto dto = sampleCreateTask();

        MvcResult result = mockMvc.perform(post(TASK).content(objectMapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertNotNull(result);
        Mockito.verify(taskRepository, Mockito.times(1)).save(Mockito.any(TaskEntity.class));

    }

    @Test
    void updateTest() throws Exception {

        TaskDto dto = sampleCreateTask();
        dto.setTitle("aktualizacja");

        MvcResult result = mockMvc.perform(put(TASK + ID_2).content(objectMapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertNotNull(result);

        TaskEntity updatedTask = taskRepository.findById(2L).orElse(null);
        assertNotNull(updatedTask);

        assertThat(updatedTask.getTitle()).isEqualTo("aktualizacja");
    }

    @Test
    void deleteTest() throws Exception {
        MvcResult result = mockMvc.perform(delete(TASK + "/3")).andExpect(status().isOk()).andReturn();
        assertNotNull(result);
    }

    @Test
    void changeStatusTest() throws Exception {
        MvcResult result = mockMvc.perform(put(TASK + ID_2 + "/status").content(objectMapper.writeValueAsString(3L)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertNotNull(result);

        TaskEntity updatedTask = taskRepository.findById(2L).orElse(null);
        assertNotNull(updatedTask);

        assertThat(updatedTask.getStatus().getId()).isEqualTo(3L);

    }

    @Test
    void assignUssersTest() throws Exception {
        MvcResult result = mockMvc.perform(put(TASK + ID_2 + "/assignUsers").content(objectMapper.writeValueAsString(Arrays.asList(1L, 2L))).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(result);
    }

    @Test
    void assignUsserTest() throws Exception {
        MvcResult result =
                mockMvc.perform(put(TASK + ID_2 + "/assignUser").content(objectMapper.writeValueAsString(3L)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertNotNull(result);
    }

    @Test
    void removeUsserTest() throws Exception {
        MvcResult result =
                mockMvc.perform(put(TASK + ID_2 + "/removeUser").content(objectMapper.writeValueAsString(2L)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertNotNull(result);
    }

    private TaskDto sampleCreateTask() {
        TaskDto result = new TaskDto();
        result.setTitle("Tytul");
        result.setDescription("Opis");
        result.setStatusId(2L);
        result.setExecuteTime(LocalDateTime.MAX);
        return result;
    }

}

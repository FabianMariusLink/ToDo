package com.github.fabianmariuslink.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabianmariuslink.backend.model.NewToDo;
import com.github.fabianmariuslink.backend.model.ToDo;
import com.github.fabianmariuslink.backend.service.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {

    private final static String BASE_URI = "/api/todos";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void addNewToDo_whenSaved_thenReturn201AndToDo() throws Exception {
        NewToDo newToDo = NewToDo.builder()
                .description("Swimming")
                .status(Status.OPEN)
                .date("2024-01-22")
                .build();

        String newToDoAsJSON = objectMapper.writeValueAsString(newToDo);

        MvcResult result = mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newToDoAsJSON)
                )
                .andExpect(status().isCreated())
                .andReturn();

        ToDo savedNewToDo = objectMapper.readValue(result.getResponse().getContentAsString(), ToDo.class);
        assertNotNull(savedNewToDo.id());
        assertEquals(savedNewToDo.description(), newToDo.description());
        assertEquals(savedNewToDo.status(), newToDo.status());
        assertEquals(savedNewToDo.date(), newToDo.date());
    }
}
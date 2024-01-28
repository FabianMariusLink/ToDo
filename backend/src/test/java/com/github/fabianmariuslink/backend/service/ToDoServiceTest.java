package com.github.fabianmariuslink.backend.service;

import com.github.fabianmariuslink.backend.model.NewToDo;
import com.github.fabianmariuslink.backend.model.ToDo;
import com.github.fabianmariuslink.backend.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ToDoServiceTest {
    private final ToDoRepository toDoRepository = mock(ToDoRepository.class);
    private final ToDoService toDoService = new ToDoService(toDoRepository);

    @Test
    void addNewToDo_whenSaved_thenReturnToDo() {
        NewToDo newToDo = NewToDo.builder()
                .description("Swimming")
                .status(Status.OPEN)
                .date("2024-01-22")
                .build();

        ToDo expected = ToDo.builder()
                .id("65a846135d3b161af8e1d8f5")
                .description("A short text for example.")
                .status(Status.OPEN)
                .date("2024-01-22")
                .build();

        when(toDoRepository.save(any(ToDo.class))).thenReturn(expected);

        ToDo actual = toDoService.addNewToDo(newToDo);
        verify(toDoRepository).save(any(ToDo.class));
        assertEquals(expected, actual);
    }
}
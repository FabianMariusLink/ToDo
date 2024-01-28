package com.github.fabianmariuslink.backend.service;

import com.github.fabianmariuslink.backend.model.NewToDo;
import com.github.fabianmariuslink.backend.model.ToDo;
import com.github.fabianmariuslink.backend.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDo addNewToDo(NewToDo newToDo) {
        return toDoRepository.save(ToDo.builder()
                .description(newToDo.description())
                .status(newToDo.status())
                .date(newToDo.date())
                .build());
    }
}
package com.github.fabianmariuslink.backend.controller;

import com.github.fabianmariuslink.backend.model.NewToDo;
import com.github.fabianmariuslink.backend.model.ToDo;
import com.github.fabianmariuslink.backend.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDo addNewToDo(@RequestBody NewToDo newToDo) {
        return toDoService.addNewToDo(newToDo);
    }
}
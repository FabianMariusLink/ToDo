package com.github.fabianmariuslink.backend.repository;

import com.github.fabianmariuslink.backend.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
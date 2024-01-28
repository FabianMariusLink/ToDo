package com.github.fabianmariuslink.backend.model;

import com.github.fabianmariuslink.backend.service.Status;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
@Builder
public record ToDo(
        @Id
        String id,
        String description,
        Status status,
        String date
) {
}
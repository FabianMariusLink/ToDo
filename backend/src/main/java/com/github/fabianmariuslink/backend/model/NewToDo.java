package com.github.fabianmariuslink.backend.model;

import com.github.fabianmariuslink.backend.service.Status;
import lombok.Builder;

@Builder
public record NewToDo(
        String description,
        Status status,
        String date
) {
}
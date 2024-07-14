package com.amagana.e_learning.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CourseDtoRequest(String title, String description,
                               LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
}

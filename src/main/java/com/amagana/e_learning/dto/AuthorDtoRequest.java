package com.amagana.e_learning.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuthorDtoRequest(String firstName, String lastName, int age, String email,
                               LocalDateTime bornAt,
                               LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
}

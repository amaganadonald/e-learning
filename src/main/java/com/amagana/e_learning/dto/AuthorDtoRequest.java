package com.amagana.e_learning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuthorDtoRequest(@NotNull(message = "FirstName must not be null") String firstName,
                               @NotEmpty(message = "LastName must not be empty") String lastName,
                               @Min(value = 0, message = "age must be up than 0") int age,
                               @Email(message = "Email must be correct") String email,
                               LocalDateTime bornAt,
                               LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
}

package com.amagana.e_learning.dto;

import java.time.LocalDateTime;

public record VideosDtoRequest(String name, int size, String url, int length,
                               LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
}

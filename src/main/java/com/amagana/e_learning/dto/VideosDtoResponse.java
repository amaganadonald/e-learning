package com.amagana.e_learning.dto;

import com.amagana.e_learning.entity.Reader;

import java.time.LocalDateTime;

public record VideosDtoResponse(Long id, String name, int size, String url, int length,
                                LocalDateTime createdAt, LocalDateTime lastUpdatedAt,
                                Reader reader) {
}

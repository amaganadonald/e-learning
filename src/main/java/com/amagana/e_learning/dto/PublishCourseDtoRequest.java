package com.amagana.e_learning.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PublishCourseDtoRequest(LocalDateTime publishDate, String comment,
                                      Long authorId, Long courseId) {
}

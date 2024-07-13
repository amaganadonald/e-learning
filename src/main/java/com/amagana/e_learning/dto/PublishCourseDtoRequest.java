package com.amagana.e_learning.dto;

import java.time.LocalDateTime;

public record PublishCourseDtoRequest(LocalDateTime publishDate, String comment,
                                      Long authorId, Long courseId) {
}

package com.amagana.e_learning.dto;

import com.amagana.e_learning.entity.Author;
import com.amagana.e_learning.entity.Course;

import java.time.LocalDateTime;

public record PublishCourseDtoResponse(Long id, LocalDateTime publishDate, String comment,
                                       Author author, Course course) {
}

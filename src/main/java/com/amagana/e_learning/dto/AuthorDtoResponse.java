package com.amagana.e_learning.dto;

import com.amagana.e_learning.entity.PublishCourse;

import java.time.LocalDateTime;
import java.util.Set;

public record AuthorDtoResponse(Long id, String firstName, String lastName,
                                int age, String email, LocalDateTime bornAt,
                                Set<PublishCourse> publishCours,
                                LocalDateTime createdAt,
                                LocalDateTime lastUpdatedAt,
                                String createdBy) {
}

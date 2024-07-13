package com.amagana.e_learning.dto;

import com.amagana.e_learning.entity.PublishCourse;
import com.amagana.e_learning.entity.Section;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record CourseDtoResponse(Long id, String title, String description,
                                LocalDateTime createdAt,
                                LocalDateTime lastUpdatedAt,
                                String createdBy,
                                Set<PublishCourse> publishCourse,
                                List<Section> section) {
}

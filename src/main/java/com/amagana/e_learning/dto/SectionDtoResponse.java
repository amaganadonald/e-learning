package com.amagana.e_learning.dto;

import com.amagana.e_learning.entity.Course;
import com.amagana.e_learning.entity.Reader;

import java.time.LocalDateTime;
import java.util.List;

public record SectionDtoResponse(String name, String sectionOrder, Long id,
                                 LocalDateTime createdAt,LocalDateTime lastUpdatedAt,
                                 Course course, List<Reader> readers) {
}

package com.amagana.e_learning.dto;

import com.amagana.e_learning.entity.Resources;
import com.amagana.e_learning.entity.Section;

import java.time.LocalDateTime;

public record ReaderDtoResponse(Long id, String name, String email, String phone,
                                LocalDateTime createdAt,LocalDateTime lastUpdatedAt,
                                Resources resources, Section section) {
}

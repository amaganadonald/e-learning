package com.amagana.e_learning.service;

import com.amagana.e_learning.dto.SectionDtoRequest;
import com.amagana.e_learning.dto.SectionDtoResponse;
import com.amagana.e_learning.entity.Section;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SectionService {
    List<SectionDtoResponse> getAllSection();
    Section getSection(Long id);
    Page<SectionDtoResponse> getSections(int page, int size);
    SectionDtoResponse getSectionById(Long id);
    SectionDtoResponse createSection(SectionDtoRequest sectionDtoRequest);
    SectionDtoResponse updateSection(Long id, SectionDtoRequest sectionDtoRequest);
    void deleteSection(Long id);
}

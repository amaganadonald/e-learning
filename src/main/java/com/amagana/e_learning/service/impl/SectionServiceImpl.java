package com.amagana.e_learning.service.impl;

import com.amagana.e_learning.dto.SectionDtoRequest;
import com.amagana.e_learning.dto.SectionDtoResponse;
import com.amagana.e_learning.entity.Course;
import com.amagana.e_learning.entity.Section;
import com.amagana.e_learning.exception.EntityNotFoundException;
import com.amagana.e_learning.repository.SectionRepository;
import com.amagana.e_learning.service.CourseService;
import com.amagana.e_learning.service.SectionService;
import com.amagana.e_learning.utils.SectionMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final CourseService courseService;
    private static final SectionMapper sectionMapper = SectionMapper.instance;
    @Override
    public List<SectionDtoResponse> getAllSection() {
        return sectionRepository.findAll().stream()
                .map(sectionMapper::sectionToSectionDtoResponse)
                .toList();
    }

    @Override
    public Page<SectionDtoResponse> getSections(int page, int size) {
        return sectionRepository.findAllBy(
                PageRequest.of(page, size)
        );
    }

    @Override
    public Section getSection(Long id) {
        return sectionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Section not found with id " + id));
    }

    @Override
    public SectionDtoResponse getSectionById(Long id) {
        return sectionMapper.sectionToSectionDtoResponse(getSection(id));
    }

    @Override
    public SectionDtoResponse createSection(SectionDtoRequest sectionDtoRequest) {
        Section section = sectionMapper.sectionDtoRequestToSection(sectionDtoRequest);
        Course course = courseService.getCourse(sectionDtoRequest.courseId());
        section.setCourse(course);
        return sectionMapper.sectionToSectionDtoResponse(
                sectionRepository.save(section)
        );
    }

    @Override
    public SectionDtoResponse updateSection(Long id, SectionDtoRequest sectionDtoRequest) {
        Section section = getSection(id);
        Course course = courseService.getCourse(sectionDtoRequest.courseId());
        section.setName(sectionDtoRequest.name());
        section.setCourse(course);
        section.setSectionOrder(sectionDtoRequest.sectionOrder());
        return sectionMapper.sectionToSectionDtoResponse(
                sectionRepository.save(section)
        );
    }

    @Override
    public void deleteSection(Long id) {
        sectionRepository.deleteById(id);
    }
}

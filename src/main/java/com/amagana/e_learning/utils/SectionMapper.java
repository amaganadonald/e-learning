package com.amagana.e_learning.utils;

import com.amagana.e_learning.dto.SectionDtoRequest;
import com.amagana.e_learning.dto.SectionDtoResponse;
import com.amagana.e_learning.entity.Section;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SectionMapper {
    SectionMapper instance = Mappers.getMapper(SectionMapper.class);
    Section sectionDtoRequestToSection(SectionDtoRequest sectionDtoRequest);
    @InheritInverseConfiguration
    SectionDtoResponse sectionToSectionDtoResponse(Section section);
}

package com.amagana.e_learning.utils;

import com.amagana.e_learning.dto.CourseDtoRequest;
import com.amagana.e_learning.dto.CourseDtoResponse;
import com.amagana.e_learning.entity.Course;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
    CourseMapper instance = Mappers.getMapper(CourseMapper.class);

    Course courseDtoRequestToCourse(CourseDtoRequest courseDtoRequest);

    @InheritInverseConfiguration
    CourseDtoResponse courseDtoCourseDtoResponse(Course course);
}

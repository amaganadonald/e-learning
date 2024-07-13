package com.amagana.e_learning.utils;

import com.amagana.e_learning.dto.PublishCourseDtoRequest;
import com.amagana.e_learning.dto.PublishCourseDtoResponse;
import com.amagana.e_learning.entity.PublishCourse;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublishCourseMapper {
    PublishCourseMapper instance = Mappers.getMapper(PublishCourseMapper.class);

    PublishCourse publishCourseDtoRequestToPublishCourse(PublishCourseDtoRequest publishCourseDtoRequest);
    @InheritInverseConfiguration
    PublishCourseDtoResponse publishToPublishCourseDtoResponse(PublishCourse publishCourse);
}

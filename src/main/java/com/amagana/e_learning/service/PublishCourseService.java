package com.amagana.e_learning.service;

import com.amagana.e_learning.dto.PublishCourseDtoRequest;
import com.amagana.e_learning.dto.PublishCourseDtoResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PublishCourseService {
    List<PublishCourseDtoResponse> getAllPublishCourse();
    Page<PublishCourseDtoResponse> getPublishCourses(int page, int size);
    PublishCourseDtoResponse getPublishCourseById(Long id);
    PublishCourseDtoResponse publishCourse(PublishCourseDtoRequest publishCourseDtoRequest);;
    PublishCourseDtoResponse updatePublishCourse(Long id, PublishCourseDtoRequest publishCourseDtoRequest);
    void deletePublishCourse(Long id);
}

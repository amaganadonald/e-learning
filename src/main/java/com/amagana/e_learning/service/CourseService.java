package com.amagana.e_learning.service;

import com.amagana.e_learning.dto.CourseDtoRequest;
import com.amagana.e_learning.dto.CourseDtoResponse;
import com.amagana.e_learning.entity.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    List<CourseDtoResponse> getAllCourse();
    Course getCourse(Long id);
    Page<CourseDtoResponse> getCourses(int page, int size);
    CourseDtoResponse getCourseById(Long id);
    CourseDtoResponse createCourse(CourseDtoRequest courseDtoRequest);
    CourseDtoResponse updateCourse(Long id, CourseDtoRequest courseDtoRequest);
    void deleteCourse(Long id);
    List<CourseDtoResponse> searchCourses(CourseDtoRequest courseDtoRequest);
}

package com.amagana.e_learning.service.impl;

import com.amagana.e_learning.dto.PublishCourseDtoRequest;
import com.amagana.e_learning.dto.PublishCourseDtoResponse;
import com.amagana.e_learning.entity.Author;
import com.amagana.e_learning.entity.Course;
import com.amagana.e_learning.entity.PublishCourse;
import com.amagana.e_learning.repository.PublishCourseRepository;
import com.amagana.e_learning.service.AuthorService;
import com.amagana.e_learning.service.CourseService;
import com.amagana.e_learning.service.PublishCourseService;
import com.amagana.e_learning.utils.PublishCourseMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PublishCourseServiceImpl implements PublishCourseService {
    private final PublishCourseRepository publishCourseRepository;
    private AuthorService authorService;
    private CourseService courseService;
    private static final PublishCourseMapper publishCourseMapper = PublishCourseMapper.instance;
    @Override
    public List<PublishCourseDtoResponse> getAllPublishCourse() {
        return publishCourseRepository.findAll().stream()
                .map(publishCourseMapper::publishToPublishCourseDtoResponse)
               .toList();
    }

    @Override
    public Page<PublishCourseDtoResponse> getPublishCourses(int page, int size) {
        return publishCourseRepository.findAllBy(
                PageRequest.of(page, size)
        );
    }

    public PublishCourse getPublishCourse(Long id){
        return publishCourseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Publish course not found with id: " + id)
        );
    }

    @Override
    public PublishCourseDtoResponse getPublishCourseById(Long id) {
        return publishCourseMapper.publishToPublishCourseDtoResponse(getPublishCourse(id));
    }

    @Override
    public PublishCourseDtoResponse publishCourse(PublishCourseDtoRequest publishCourseDtoRequest) {
        PublishCourse publishCourse = publishCourseMapper.publishCourseDtoRequestToPublishCourse(publishCourseDtoRequest);
        Author author = authorService.getAuthor(publishCourseDtoRequest.authorId());
        Course course = courseService.getCourse(publishCourseDtoRequest.courseId());
        publishCourse.setAuthor(author);
        publishCourse.setCourse(course);
        return publishCourseMapper.publishToPublishCourseDtoResponse(
                publishCourseRepository.save(publishCourse)
        );
    }

    @Override
    public PublishCourseDtoResponse updatePublishCourse(Long id, PublishCourseDtoRequest publishCourseDtoRequest) {
        PublishCourse publishCourse = getPublishCourse(id);
        Author author = authorService.getAuthor(publishCourseDtoRequest.authorId());
        Course course = courseService.getCourse(publishCourseDtoRequest.courseId());
        publishCourse.setPublishDate(publishCourseDtoRequest.publishDate());
        publishCourse.setComment(publishCourseDtoRequest.comment());
        publishCourse.setAuthor(author);
        publishCourse.setCourse(course);
        return publishCourseMapper.publishToPublishCourseDtoResponse(
                publishCourseRepository.save(publishCourse)
        );
    }

    @Override
    public void deletePublishCourse(Long id) {
       publishCourseRepository.delete(getPublishCourse(id));
    }
}

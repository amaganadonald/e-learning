package com.amagana.e_learning.service.impl;

import com.amagana.e_learning.dto.CourseDtoRequest;
import com.amagana.e_learning.dto.CourseDtoResponse;
import com.amagana.e_learning.entity.Course;
import com.amagana.e_learning.exception.EntityNotFoundException;
import com.amagana.e_learning.repository.CourseRepository;
import com.amagana.e_learning.service.CourseService;
import com.amagana.e_learning.utils.CourseMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final EntityManager entityManager;
    private static final CourseMapper courseMapper = CourseMapper.instance;
    @Override
    public List<CourseDtoResponse> getAllCourse() {
        return courseRepository.findAll().stream()
                .map(courseMapper::courseDtoCourseDtoResponse)
                .toList();
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Course not found with id " + id)
        );
    }

    @Override
    public Page<CourseDtoResponse> getCourses(int page, int size) {
        return courseRepository.findAllBy(PageRequest.of(page, size, Sort.by("title").ascending()));
    }

    @Override
    public CourseDtoResponse getCourseById(Long id) {
        return courseMapper.courseDtoCourseDtoResponse(getCourse(id));
    }

    @Override
    public CourseDtoResponse createCourse(CourseDtoRequest courseDtoRequest) {
        return courseMapper.courseDtoCourseDtoResponse(
                courseRepository.save(courseMapper.courseDtoRequestToCourse(courseDtoRequest))
        );
    }

    @Override
    public CourseDtoResponse updateCourse(Long id, CourseDtoRequest courseDtoRequest) {
        Course course = getCourse(id);
        course.setTitle(courseDtoRequest.title());
        course.setDescription(courseDtoRequest.description());
        return courseMapper.courseDtoCourseDtoResponse(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(Long id) {
      courseRepository.delete(getCourse(id));
    }

    @Override
    public List<CourseDtoResponse> searchCourses(CourseDtoRequest courseDtoRequest){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        criteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if(courseDtoRequest.title() != null && !courseDtoRequest.title().isEmpty()){
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + courseDtoRequest.title() + "%"));
        }
        if(courseDtoRequest.description()!= null &&!courseDtoRequest.description().isEmpty()){
            predicates.add(criteriaBuilder.like(root.get("description"), "%" + courseDtoRequest.description() + "%"));
        }
        if(courseDtoRequest.createdAt()!= null){
            predicates.add(criteriaBuilder.equal(root.get("createdAt"), courseDtoRequest.createdAt()));
        }
        if(!predicates.isEmpty()){
            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        }
        return courseRepository.findAll((Sort) criteriaQuery).stream()
                .map(courseMapper::courseDtoCourseDtoResponse)
                .toList();
    }
}

package com.amagana.e_learning.repository;

import com.amagana.e_learning.dto.CourseDtoResponse;
import com.amagana.e_learning.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<CourseDtoResponse> findAllBy(Pageable pageable);
}

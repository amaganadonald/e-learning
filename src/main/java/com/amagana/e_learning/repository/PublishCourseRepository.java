package com.amagana.e_learning.repository;

import com.amagana.e_learning.dto.PublishCourseDtoResponse;
import com.amagana.e_learning.entity.PublishCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishCourseRepository extends JpaRepository<PublishCourse, Long> {
    Page<PublishCourseDtoResponse> findAllBy(Pageable pageable);
}

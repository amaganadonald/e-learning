package com.amagana.e_learning.repository;

import com.amagana.e_learning.dto.SectionDtoResponse;
import com.amagana.e_learning.entity.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Page<SectionDtoResponse> findAllBy(Pageable pageable);
}

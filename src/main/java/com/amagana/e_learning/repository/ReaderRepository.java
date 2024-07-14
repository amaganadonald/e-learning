package com.amagana.e_learning.repository;

import com.amagana.e_learning.dto.ReaderDtoResponse;
import com.amagana.e_learning.entity.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Page<ReaderDtoResponse> findAllBy(Pageable pageable);
}

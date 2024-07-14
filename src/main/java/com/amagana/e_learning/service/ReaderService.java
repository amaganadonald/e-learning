package com.amagana.e_learning.service;

import com.amagana.e_learning.dto.ReaderDtoRequest;
import com.amagana.e_learning.dto.ReaderDtoResponse;
import com.amagana.e_learning.entity.Reader;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReaderService {
    List<ReaderDtoResponse> getAllReader();
    Page<ReaderDtoResponse> getReaders(int page, int size);
    ReaderDtoResponse getReaderById(Long id);
    ReaderDtoResponse createReader(ReaderDtoRequest readerDtoRequest);
    ReaderDtoResponse updateReader(Long id, ReaderDtoRequest readerDtoRequest);
    void deleteReader(Long id);
    Reader getReader(Long id);
    ReaderDtoResponse assignReaderSection(long sectionId, long readerId);
}

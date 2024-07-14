package com.amagana.e_learning.service.impl;

import com.amagana.e_learning.dto.ReaderDtoRequest;
import com.amagana.e_learning.dto.ReaderDtoResponse;
import com.amagana.e_learning.entity.Reader;
import com.amagana.e_learning.entity.Section;
import com.amagana.e_learning.exception.EntityNotFoundException;
import com.amagana.e_learning.repository.ReaderRepository;
import com.amagana.e_learning.service.ReaderService;
import com.amagana.e_learning.service.SectionService;
import com.amagana.e_learning.utils.ReaderMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;
    private final SectionService sectionService;
    private static final ReaderMapper readerMapper = ReaderMapper.instance;
    @Override
    public List<ReaderDtoResponse> getAllReader() {
        return readerRepository.findAll().stream()
                .map(readerMapper::readerToReaderDtoResponse)
                .toList();
    }

    @Override
    public Page<ReaderDtoResponse> getReaders(int page, int size) {
        return readerRepository.findAllBy(
                PageRequest.of(page, size)
        );
    }

    @Override
    public ReaderDtoResponse getReaderById(Long id) {
        return readerMapper.readerToReaderDtoResponse(getReader(id));
    }

    @Override
    public ReaderDtoResponse createReader(ReaderDtoRequest readerDtoRequest) {
        return readerMapper.readerToReaderDtoResponse(
                readerRepository.save(readerMapper.readerDtoRequestToReader(readerDtoRequest))
        );
    }

    @Override
    public ReaderDtoResponse updateReader(Long id, ReaderDtoRequest readerDtoRequest) {
        Reader reader = getReader(id);
        reader.setEmail(readerDtoRequest.email());
        reader.setName(readerDtoRequest.name());
        reader.setPhone(readerDtoRequest.phone());
        return readerMapper.readerToReaderDtoResponse(
                readerRepository.save(reader)
        );
    }

    @Override
    public void deleteReader(Long id) {
       readerRepository.delete(getReader(id));
    }

    @Override
    public Reader getReader(Long id) {
        return readerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Reader not found for id " + id));
    }

    @Override
    public ReaderDtoResponse assignReaderSection(long sectionId, long readerId) {
        Reader reader = getReader(readerId);
        Section section = sectionService.getSection(sectionId);
        reader.setSection(section);
        section.getReaders().add(reader);
        return readerMapper.readerToReaderDtoResponse(
                readerRepository.save(reader)
        );
    }
}

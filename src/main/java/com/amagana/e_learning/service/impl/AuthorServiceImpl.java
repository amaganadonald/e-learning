package com.amagana.e_learning.service.impl;

import com.amagana.e_learning.config.AuthorSpecification;
import com.amagana.e_learning.dto.AuthorDtoRequest;
import com.amagana.e_learning.dto.AuthorDtoResponse;
import com.amagana.e_learning.dto.SearchAuthorDto;
import com.amagana.e_learning.entity.Author;
import com.amagana.e_learning.exception.EntityNotFoundException;
import com.amagana.e_learning.repository.AuthorRepository;
import com.amagana.e_learning.service.AuthorService;
import com.amagana.e_learning.utils.AuthorMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private static final AuthorMapper authorMapper = AuthorMapper.instance;
    @Override
    public List<AuthorDtoResponse> getAllAuthor() {
        return authorRepository.findAll().stream()
                .map(authorMapper::authorToAuthorDtoResponse)
                .toList();
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Author not found with id " + id));
    }

    @Override
    public Page<AuthorDtoResponse> getAuthors(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("firstName").ascending());
        return authorRepository.findAllBy(pageRequest).map(authorMapper::authorToAuthorDtoResponse);
    }

    @Override
    public AuthorDtoResponse getAuthorById(Long id) {
        return authorMapper.authorToAuthorDtoResponse(getAuthor(id));
    }

    @Override
    public AuthorDtoResponse createAuthor(AuthorDtoRequest authorDtoRequest) {
        return authorMapper.authorToAuthorDtoResponse(
                authorRepository.save(authorMapper.authorDtoRequestToAuthor(authorDtoRequest))
        );
    }

    @Override
    public AuthorDtoResponse updateAuthor(Long id, AuthorDtoRequest authorDtoRequest) {
        Author author = getAuthor(id);
        author.setFirstName(authorDtoRequest.firstName());
        author.setLastName(authorDtoRequest.lastName());
        author.setAge(authorDtoRequest.age());
        author.setEmail(authorDtoRequest.email());
        author.setBornAt(authorDtoRequest.bornAt());
        return authorMapper.authorToAuthorDtoResponse(
                authorRepository.save(author)
        );
    }

    @Override
    public void deleteAuthor(Long id) {
       authorRepository.delete(getAuthor(id));
    }

    @Override
    public List<AuthorDtoResponse> getAuthorSpecification(SearchAuthorDto searchAuthorDto) {
        Specification<Author> spec = Specification.where(
                AuthorSpecification.hasAge(searchAuthorDto.age())
                        .or(AuthorSpecification.firstnameLike(searchAuthorDto.firstName()))
                        .or(AuthorSpecification.emailContains(searchAuthorDto.email()))
        );
        return authorRepository.findAll(spec).stream()
                .map(authorMapper::authorToAuthorDtoResponse)
                .toList();
    }
}

package com.amagana.e_learning.service;

import com.amagana.e_learning.dto.AuthorDtoRequest;
import com.amagana.e_learning.dto.AuthorDtoResponse;
import com.amagana.e_learning.dto.SearchAuthorDto;
import com.amagana.e_learning.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface AuthorService {
    List<AuthorDtoResponse> getAllAuthor();
    Author getAuthor(Long id);
    Page<AuthorDtoResponse> getAuthors(int page, int size);
    AuthorDtoResponse getAuthorById(Long id);
    AuthorDtoResponse createAuthor(AuthorDtoRequest authorDtoRequest);
    AuthorDtoResponse updateAuthor(Long id, AuthorDtoRequest authorDtoRequest);
    void deleteAuthor(Long id);
    List<AuthorDtoResponse> getAuthorSpecification(SearchAuthorDto searchAuthorDto);
}

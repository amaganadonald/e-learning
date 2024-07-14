package com.amagana.e_learning.controller;

import com.amagana.e_learning.dto.AuthorDtoRequest;
import com.amagana.e_learning.dto.AuthorDtoResponse;
import com.amagana.e_learning.dto.SearchAuthorDto;
import com.amagana.e_learning.enums.StatusResponse;
import com.amagana.e_learning.models.APIResponse;
import com.amagana.e_learning.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<APIResponse<List<AuthorDtoResponse>>> getAllAuthor() {
        List<AuthorDtoResponse> authorDtoResponses = authorService.getAllAuthor();
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.multipleResults(
                StatusResponse.SUCCESS, authorDtoResponses
        ));
    }
    @GetMapping("/pages")
    public ResponseEntity<APIResponse<Page<AuthorDtoResponse>>> getAllAuthorPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<AuthorDtoResponse> authorDtoResponsePage = authorService.getAuthors(page, size);
        return ResponseEntity.ok(APIResponse.pageResults(
                StatusResponse.SUCCESS, authorDtoResponsePage
        ));
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AuthorDtoResponse>> getAuthorById(@PathVariable Long id) {
        AuthorDtoResponse authorDtoResponse = authorService.getAuthorById(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, authorDtoResponse
        ));
    }
    @PostMapping
    public ResponseEntity<APIResponse<AuthorDtoResponse>> createAuthor(@RequestBody @Valid AuthorDtoRequest authorDtoRequest) {
        AuthorDtoResponse authorDtoResponse = authorService.createAuthor(authorDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.singeResult(
                StatusResponse.SUCCESS, authorDtoResponse
        ));
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<AuthorDtoResponse>> updateAuthor(@PathVariable Long id, @RequestBody @Valid AuthorDtoRequest authorDtoRequest) {
        AuthorDtoResponse authorDtoResponse = authorService.updateAuthor(id, authorDtoRequest);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, authorDtoResponse
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, "Author deleted successfully"
        ));
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<AuthorDtoResponse>>> searchAuthor(@Valid @RequestBody SearchAuthorDto searchAuthorDto) {
        List<AuthorDtoResponse> authorDtoResponses = authorService.getAuthorSpecification(searchAuthorDto);
        return ResponseEntity.ok(APIResponse.multipleResults(
                StatusResponse.SUCCESS, authorDtoResponses
        ));
    }
}

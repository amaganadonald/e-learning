package com.amagana.e_learning.controller;

import com.amagana.e_learning.dto.ReaderDtoRequest;
import com.amagana.e_learning.dto.ReaderDtoResponse;
import com.amagana.e_learning.enums.StatusResponse;
import com.amagana.e_learning.models.APIResponse;
import com.amagana.e_learning.service.ReaderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reader")
@AllArgsConstructor
public class ReaderController {
    private  final ReaderService readerService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ReaderDtoResponse>>> getAllReader() {
        List<ReaderDtoResponse> readerDtoResponses = readerService.getAllReader();
        return ResponseEntity.ok(APIResponse.multipleResults(
                StatusResponse.SUCCESS, readerDtoResponses
        ));
    }
    @GetMapping("/page")
    public ResponseEntity<APIResponse<Page<ReaderDtoResponse>>> getAllReaderWithPagination(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<ReaderDtoResponse> readerDtoResponses = readerService.getReaders(page, size);
        return ResponseEntity.ok(APIResponse.pageResults(
                StatusResponse.SUCCESS, readerDtoResponses
        ));
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ReaderDtoResponse>> getReaderById(@PathVariable Long id) {
        ReaderDtoResponse readerDtoResponse = readerService.getReaderById(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, readerDtoResponse
        ));
    }
    @PostMapping
    public ResponseEntity<APIResponse<ReaderDtoResponse>> createReader(@RequestBody @Valid ReaderDtoRequest readerDtoRequest) {
        ReaderDtoResponse createdReaderDtoResponse = readerService.createReader(readerDtoRequest);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, createdReaderDtoResponse
        ));
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ReaderDtoResponse>> updateReader(@PathVariable Long id,
                                                                   @RequestBody @Valid ReaderDtoRequest readerDtoRequest) {
        ReaderDtoResponse updatedReaderDtoResponse = readerService.updateReader(id, readerDtoRequest);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, updatedReaderDtoResponse
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, "Reader successfully deleted"
        ));
    }
    @PostMapping("/{readerId}/{sectionId}")
    public ResponseEntity<APIResponse<ReaderDtoResponse>> addReaderToSection(@PathVariable Long readerId,
                                                                 @PathVariable Long sectionId) {
        ReaderDtoResponse readerDtoResponse = readerService.assignReaderSection(sectionId, readerId);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, readerDtoResponse
        ));
    }
}

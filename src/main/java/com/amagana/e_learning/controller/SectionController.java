package com.amagana.e_learning.controller;

import com.amagana.e_learning.dto.SectionDtoRequest;
import com.amagana.e_learning.dto.SectionDtoResponse;
import com.amagana.e_learning.enums.StatusResponse;
import com.amagana.e_learning.models.APIResponse;
import com.amagana.e_learning.service.SectionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/section")
@AllArgsConstructor
public class SectionController {
    private final SectionService sectionService;

    @GetMapping
    public ResponseEntity<APIResponse<List<SectionDtoResponse>>> getAllSection() {
        List<SectionDtoResponse> sections = sectionService.getAllSection();
        return ResponseEntity.ok(APIResponse.multipleResults(
                StatusResponse.SUCCESS, sections
        ));
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<SectionDtoResponse>> getSectionById(@PathVariable Long id) {
        SectionDtoResponse section = sectionService.getSectionById(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, section
        ));
    }
    @GetMapping("/page")
    public ResponseEntity<APIResponse<Page<SectionDtoResponse>>> getAllSectionByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<SectionDtoResponse> sections = sectionService.getSections(page, size);
        return ResponseEntity.ok(APIResponse.pageResults(
                StatusResponse.SUCCESS, sections
        ));
    }
    @PostMapping
    public ResponseEntity<APIResponse<SectionDtoResponse>> createSection(@RequestBody @Valid SectionDtoRequest sectionDtoRequest) {
        SectionDtoResponse createdSection = sectionService.createSection(sectionDtoRequest);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, createdSection
        ));
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<SectionDtoResponse>> updateSection(@PathVariable Long id,
                                                                         @RequestBody @Valid SectionDtoRequest sectionDtoRequest) {
        SectionDtoResponse updatedSection = sectionService.updateSection(id, sectionDtoRequest);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, updatedSection
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, "Section deleted successfully"
        ));
    }
}

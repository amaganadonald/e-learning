package com.amagana.e_learning.controller;

import com.amagana.e_learning.dto.PublishCourseDtoRequest;
import com.amagana.e_learning.dto.PublishCourseDtoResponse;
import com.amagana.e_learning.enums.StatusResponse;
import com.amagana.e_learning.models.APIResponse;
import com.amagana.e_learning.service.PublishCourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/publishCourse")
@AllArgsConstructor
public class PublishCourseController {
    public final PublishCourseService publishCourseService;

    @GetMapping
    public ResponseEntity<APIResponse<List<PublishCourseDtoResponse>>> getAllPublishCourse() {
        List<PublishCourseDtoResponse> publishCourseDtoResponses = publishCourseService.getAllPublishCourse();
        return ResponseEntity.ok(APIResponse.multipleResults(
                StatusResponse.SUCCESS, publishCourseDtoResponses
        ));
    }
    @GetMapping("/page")
    public ResponseEntity<APIResponse<Page<PublishCourseDtoResponse>>> getAllPublishCourseByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<PublishCourseDtoResponse> publishCourseDtoResponses = publishCourseService.getPublishCourses(page, size);
        return ResponseEntity.ok(APIResponse.pageResults(
                StatusResponse.SUCCESS, publishCourseDtoResponses
        ));
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<PublishCourseDtoResponse>> getPublishCourseById(@PathVariable Long id) {
        PublishCourseDtoResponse publishCourseDtoResponse = publishCourseService.getPublishCourseById(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, publishCourseDtoResponse
        ));
    }
    @PostMapping
    public ResponseEntity<APIResponse<PublishCourseDtoResponse>> createPublishCourse(
            @RequestBody @Valid PublishCourseDtoRequest publishCourseDtoRequest) {
        PublishCourseDtoResponse createdPublishCourseDtoResponse = publishCourseService.publishCourse(publishCourseDtoRequest);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, createdPublishCourseDtoResponse
        ));
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<PublishCourseDtoResponse>> updatePublishCourse(
            @PathVariable Long id,
            @RequestBody @Valid PublishCourseDtoRequest publishCourseDtoRequest) {
        PublishCourseDtoResponse updatedPublishCourseDtoResponse = publishCourseService.updatePublishCourse(id, publishCourseDtoRequest);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, updatedPublishCourseDtoResponse
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deletePublishCourse(@PathVariable Long id) {
        publishCourseService.deletePublishCourse(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, "Publish course Successfully deleted"
        ));
    }
}

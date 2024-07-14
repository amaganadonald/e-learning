package com.amagana.e_learning.controller;

import com.amagana.e_learning.dto.CourseDtoRequest;
import com.amagana.e_learning.dto.CourseDtoResponse;
import com.amagana.e_learning.enums.StatusResponse;
import com.amagana.e_learning.models.APIResponse;
import com.amagana.e_learning.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<APIResponse<List<CourseDtoResponse>>> getAllCourses() {
        List<CourseDtoResponse> courseDtoResponses = courseService.getAllCourse();
        return ResponseEntity.ok().body(APIResponse.multipleResults(
                StatusResponse.SUCCESS, courseDtoResponses
        ));
    }
    @GetMapping("/page")
    public ResponseEntity<APIResponse<Page<CourseDtoResponse>>> getCoursesPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        Page<CourseDtoResponse> courseDtoResponses = courseService.getCourses(page, size);
        return ResponseEntity.ok().body(APIResponse.pageResults(
                StatusResponse.SUCCESS, courseDtoResponses
        ));
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CourseDtoResponse>> getCourseById(@PathVariable Long id) {
        CourseDtoResponse courseDtoResponse = courseService.getCourseById(id);
        return ResponseEntity.ok().body(APIResponse.singeResult(
                StatusResponse.SUCCESS, courseDtoResponse
        ));
    }
    @PostMapping
    public ResponseEntity<APIResponse<CourseDtoResponse>> createCourse(@RequestBody @Valid CourseDtoRequest courseDtoRequest) {
        CourseDtoResponse createdCourseDtoResponse = courseService.createCourse(courseDtoRequest);
        return ResponseEntity.ok().body(APIResponse.singeResult(
                StatusResponse.SUCCESS, createdCourseDtoResponse
        ));
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<CourseDtoResponse>> updateCourse(@PathVariable Long id,
                                                                       @RequestBody @Valid CourseDtoRequest courseDtoRequest) {
        CourseDtoResponse courseDtoResponse = courseService.updateCourse(id, courseDtoRequest);
        return ResponseEntity.ok().body(APIResponse.singeResult(
                StatusResponse.SUCCESS, courseDtoResponse
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().body(APIResponse.singeResult(
                StatusResponse.SUCCESS, "Course deleted successfully"));
    }
    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<CourseDtoResponse>>> searchCourses(@Valid @RequestBody CourseDtoRequest courseDtoRequest) {
        List<CourseDtoResponse> courseDtoResponses = courseService.searchCourses(courseDtoRequest);
        return ResponseEntity.ok().body(APIResponse.multipleResults(
                StatusResponse.SUCCESS, courseDtoResponses
        ));
    }
}

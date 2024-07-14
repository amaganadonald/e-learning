package com.amagana.e_learning.controller;

import com.amagana.e_learning.dto.VideosDtoRequest;
import com.amagana.e_learning.dto.VideosDtoResponse;
import com.amagana.e_learning.enums.StatusResponse;
import com.amagana.e_learning.models.APIResponse;
import com.amagana.e_learning.service.VideosService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
@AllArgsConstructor
public class VideosController {
    private final VideosService videosService;

    @GetMapping
    public ResponseEntity<APIResponse<List<VideosDtoResponse>>> getAllVideos() {
        List<VideosDtoResponse> videos = videosService.getAllVideos();
        return ResponseEntity.ok(APIResponse.multipleResults(
                StatusResponse.SUCCESS,videos));
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<VideosDtoResponse>> getAllVideosById(@PathVariable Long id) {
        VideosDtoResponse video = videosService.getVideoById(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, video));
    }
    @PostMapping
    public ResponseEntity<APIResponse<VideosDtoResponse>> createVideo(@RequestBody @Valid VideosDtoRequest videosDtoRequest) {
        VideosDtoResponse createdVideo = videosService.createVideo(videosDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.singeResult(
                StatusResponse.SUCCESS, createdVideo));
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<VideosDtoResponse>> updateVideo(@PathVariable Long id,
                                                                 @RequestBody @Valid VideosDtoRequest videosDtoRequest) {
        VideosDtoResponse updatedVideo = videosService.updateVideo(id, videosDtoRequest);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, updatedVideo));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteVideo(@PathVariable Long id) {
        videosService.deleteVideo(id);
        return ResponseEntity.ok(APIResponse.singeResult(
                StatusResponse.SUCCESS, "Video deleted successfully"));
    }
}

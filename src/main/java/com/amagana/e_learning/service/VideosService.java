package com.amagana.e_learning.service;

import com.amagana.e_learning.dto.VideosDtoRequest;
import com.amagana.e_learning.dto.VideosDtoResponse;

import java.util.List;

public interface VideosService {
    List<VideosDtoResponse> getAllVideos();
    VideosDtoResponse getVideoById(Long id);
    VideosDtoResponse createVideo(VideosDtoRequest videosDtoRequest);
    VideosDtoResponse updateVideo(Long id, VideosDtoRequest videosDtoRequest);
    void deleteVideo(Long id);
}

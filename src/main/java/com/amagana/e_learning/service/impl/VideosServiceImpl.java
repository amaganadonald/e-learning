package com.amagana.e_learning.service.impl;

import com.amagana.e_learning.dto.VideosDtoRequest;
import com.amagana.e_learning.dto.VideosDtoResponse;
import com.amagana.e_learning.entity.Videos;
import com.amagana.e_learning.exception.EntityNotFoundException;
import com.amagana.e_learning.repository.VideosRepository;
import com.amagana.e_learning.service.VideosService;
import com.amagana.e_learning.utils.VideosMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class VideosServiceImpl implements VideosService {
    private final VideosRepository videosRepository;
    private static final VideosMapper videosMapper = VideosMapper.instance;
    @Override
    public List<VideosDtoResponse> getAllVideos() {
        return videosRepository.findAll().stream()
                .map(videosMapper::videosToVideosDtoResponse)
                .toList();
    }

    public Videos getAllVideos(Long id){
        return videosRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Videos not found with id " + id)
        );
    }

    @Override
    public VideosDtoResponse getVideoById(Long id) {
        return videosMapper.videosToVideosDtoResponse(getAllVideos(id));
    }

    @Override
    public VideosDtoResponse createVideo(VideosDtoRequest videosDtoRequest) {
        return videosMapper.videosToVideosDtoResponse(
                videosRepository.save(videosMapper.videosDtoRequestToVideos(videosDtoRequest))
        );
    }

    @Override
    public VideosDtoResponse updateVideo(Long id, VideosDtoRequest videosDtoRequest) {
        Videos videos = getAllVideos(id);
        videos.setLength(videosDtoRequest.length());
        videos.setName(videosDtoRequest.name());
        videos.setSize(videosDtoRequest.size());
        videos.setUrl(videosDtoRequest.url());
        videos.setLastUpdatedAt(videosDtoRequest.lastUpdatedAt());
        return videosMapper.videosToVideosDtoResponse(
                videosRepository.save(videos)
        );
    }

    @Override
    public void deleteVideo(Long id) {
      videosRepository.delete(getAllVideos(id));
    }
}

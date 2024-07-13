package com.amagana.e_learning.utils;

import com.amagana.e_learning.dto.VideosDtoRequest;
import com.amagana.e_learning.dto.VideosDtoResponse;
import com.amagana.e_learning.entity.Videos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideosMapper {
    VideosMapper instance = Mappers.getMapper(VideosMapper.class);
    Videos videosDtoRequestToVideos(VideosDtoRequest videosDtoRequest);
    @InheritInverseConfiguration
    VideosDtoResponse videosToVideosDtoResponse(Videos videos);
}

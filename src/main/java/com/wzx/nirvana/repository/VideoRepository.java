package com.wzx.nirvana.repository;

import com.wzx.nirvana.model.Video;

import java.util.List;

public interface VideoRepository {

    List<Video> getVideos();

    String getVideoList();

    Video addVideo(Video video);

    int updateVideo(Video video);

}

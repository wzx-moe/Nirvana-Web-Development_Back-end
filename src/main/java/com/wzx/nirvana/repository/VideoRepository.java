package com.wzx.nirvana.repository;

import com.wzx.nirvana.model.Video;

import java.util.List;

public interface VideoRepository {

    List<Video> getVideos();

    Video addVideo(Video video);

    int updateVideo(Video video);

    long deleteVideo(String id);

}

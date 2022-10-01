package com.wzx.nirvana.repository.impl;

import com.wzx.nirvana.model.Video;
import com.wzx.nirvana.repository.VideoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoRepositoryImpl implements VideoRepository {
    @Override
    public List<Video> getVideos() {
        return null;
    }

    @Override
    public String getVideoList() {
        return null;
    }

    @Override
    public Video addVideo(Video video) {
        return null;
    }

    @Override
    public int updateVideo(Video video) {
        return 0;
    }
}

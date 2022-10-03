package com.wzx.nirvana.repository.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wzx.nirvana.model.Video;
import com.wzx.nirvana.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoRepositoryImpl implements VideoRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Video> getVideos() {
        return mongoTemplate.findAll(Video.class);
    }

    @Override
    public Video addVideo(Video video) {
        return mongoTemplate.save(video);
    }

    @Override
    public int updateVideo(Video video) {
        Query query = new Query(Criteria.where("id").is(video.getId()));
        Update update = new Update();
        update.set("name", video.getName());
        update.set("description", video.getDescription());
        update.set("url", video.getUrl());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Video.class);

        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }

    @Override
    public long deleteVideo(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query, Video.class);
        if (result != null) {
            return result.getDeletedCount();
        }
        return 0;
    }
}

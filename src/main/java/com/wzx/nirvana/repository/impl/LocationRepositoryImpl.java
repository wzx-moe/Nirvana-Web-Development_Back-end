package com.wzx.nirvana.repository.impl;

import com.mongodb.client.result.UpdateResult;
import com.wzx.nirvana.model.Location;
import com.wzx.nirvana.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class LocationRepositoryImpl implements LocationRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public int updateLocation(Location location) {
        Query query = new Query();
        Update update = new Update();
        update.set("longitude", location.getLongitude());
        update.set("latitude", location.getLatitude());
        update.set("url", location.getUrl());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Location.class);
        if (result.getMatchedCount() == 0) {
            mongoTemplate.save(location);
        }

        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }

    @Override
    public Location getLocation() {
        Query query = new Query();
        return mongoTemplate.findOne(query, Location.class);
    }

}

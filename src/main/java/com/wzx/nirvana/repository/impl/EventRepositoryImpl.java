package com.wzx.nirvana.repository.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wzx.nirvana.model.Event;
import com.wzx.nirvana.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public List<Event> getEvents() {
        return mongoTemplate.findAll(Event.class);
    }

    @Override
    public List<Event> getEvents(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        Query query = new Query(Criteria.where("dateTime").lte(fmt.format(date)));
        return mongoTemplate.find(query, Event.class);
    }

    @Override
    public Event addEvent(Event event) {
        return mongoTemplate.save(event);
    }

    @Override
    public int updateEvent(Event event) {
        Query query = new Query(Criteria.where("id").is(event.getId()));
        Update update = new Update();
        update.set("name", event.getName());
        update.set("dateTime", event.getDateTime());
        update.set("duration", event.getDuration());
        update.set("description", event.getDescription());
        update.set("repeat", event.getRepeat());
        update.set("repeatCount", event.getRepeatCount());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Event.class);
        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }

    @Override
    public long deleteEvent(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query, Event.class);
        if (result != null) {
            return result.getDeletedCount();
        }
        return 0;
    }
}

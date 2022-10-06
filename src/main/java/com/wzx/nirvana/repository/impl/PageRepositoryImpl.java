package com.wzx.nirvana.repository.impl;

import com.mongodb.client.result.UpdateResult;
import com.wzx.nirvana.model.Page;
import com.wzx.nirvana.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class PageRepositoryImpl implements PageRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page getOne(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Page.class);
    }

    @Override
    public int update(Page page) {
        Query query = new Query(Criteria.where("name").is(page.getName()));
        Update update = new Update();
        update.set("name", page.getName());
        update.set("content", page.getContent());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Page.class);
        if (result.getMatchedCount() == 0) {
            mongoTemplate.save(page);
        }

        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }
}

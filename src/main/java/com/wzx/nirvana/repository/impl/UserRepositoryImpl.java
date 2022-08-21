package com.wzx.nirvana.repository.impl;

import com.mongodb.client.result.UpdateResult;
import com.wzx.nirvana.model.User;
import com.wzx.nirvana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public User getById(String name) {
        Query query = new Query(Criteria.where("id").is(name));
        return mongoTemplate.findOne(query, User.class);
    }

    public User getOne(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public int update(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        update.set("name", user.getName());
        update.set("password", user.getPassword());
        update.set("loginState", user.isLoginState());
        update.set("sessionId", user.getSessionId());

        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);

        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }
}

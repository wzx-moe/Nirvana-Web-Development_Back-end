package com.wzx.nirvana.repository.impl;

import com.mongodb.client.result.UpdateResult;
import com.wzx.nirvana.model.Code;
import com.wzx.nirvana.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CodeRepositoryImpl implements CodeRepository {


    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Code getOne(String code) {
        Query query = new Query(Criteria.where("code").is(code));
        return mongoTemplate.findOne(query, Code.class);
    }

    @Override
    public Code addCode(Code code) {
        String uuid = "";
        do {
            uuid = UUID.randomUUID().toString();
            uuid = uuid.substring(uuid.length() - 6);
        } while (this.getOne(uuid) != null);

        code.setCode(uuid);
        return mongoTemplate.save(code);
    }

    @Override
    public int updateCode(Code code) {
        Query query = new Query(Criteria.where("code").is(code.getCode()));
        Update update = new Update();
        update.set("code", code.getCode());
        update.set("url", code.getUrl());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Code.class);

        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }
}

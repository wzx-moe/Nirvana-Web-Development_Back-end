package com.wzx.nirvana.repository.impl;

import com.mongodb.client.result.UpdateResult;
import com.wzx.nirvana.model.User;
import com.wzx.nirvana.model.VerificationCode;
import com.wzx.nirvana.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class VerificationCodeRepositoryImpl implements VerificationCodeRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public String getSessionId(String sessionId) {
        Query query = new Query(Criteria.where("sessionId").is(sessionId));
        VerificationCode verificationCode = mongoTemplate.findOne(query, VerificationCode.class);
        if (verificationCode != null) return verificationCode.getSessionId();
        return null;
    }

    @Override
    public int addVerCode(String verCode, String sessionId) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setVerCode(verCode);
        verificationCode.setSessionId(sessionId);
        VerificationCode result = mongoTemplate.save(verificationCode);
        if (result != null)
            return 1;
        else
            return 0;
    }

    @Override
    public int updateVerCode(String verCode, String sessionId) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setVerCode(verCode);
        verificationCode.setSessionId(sessionId);
        Query query = new Query(Criteria.where("sessionId").is(sessionId));
        Update update = new Update();
        update.set("verCode", verCode);

        UpdateResult result = mongoTemplate.updateFirst(query, update, VerificationCode.class);

        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }

    @Override
    public int deleteSessionId(String sessionId) {
        Query query = new Query(Criteria.where("sessionId").is(sessionId));
        Update update = new Update();
        update.set(null, sessionId);

        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);

        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }

    @Override
    public String getVerCode(String sessionId) {
        Query query = new Query(Criteria.where("sessionId").is(sessionId));
        VerificationCode verificationCode = mongoTemplate.findOne(query, VerificationCode.class);
        if (verificationCode != null) return verificationCode.getVerCode();
        return null;
    }
}

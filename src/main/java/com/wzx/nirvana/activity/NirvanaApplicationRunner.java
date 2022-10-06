package com.wzx.nirvana.activity;

import com.wzx.nirvana.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class NirvanaApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(NirvanaApplicationRunner.class);
    @Value("${Nirvana.username}")
    public String name;
    @Value("${Nirvana.password}")
    public String password;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Query query = new Query(Criteria.where("name").is("Nirvana"));
        mongoTemplate.findAllAndRemove(query, User.class);

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        mongoTemplate.save(user);

    }
}
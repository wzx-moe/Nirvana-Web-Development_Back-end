package com.wzx.nirvana.repository.impl;

import com.wzx.nirvana.model.Contact;
import com.wzx.nirvana.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Contact addContact(Contact contact) {
        return mongoTemplate.save(contact);
    }

    @Override
    public List<Contact> getContacts() {
        return mongoTemplate.findAll(Contact.class);
    }
}

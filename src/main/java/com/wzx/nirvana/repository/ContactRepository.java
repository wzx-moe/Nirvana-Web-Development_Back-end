package com.wzx.nirvana.repository;

import com.wzx.nirvana.model.Contact;

import java.util.List;

public interface ContactRepository {

    Contact addContact(Contact contact);

    List<Contact> getContacts();

}

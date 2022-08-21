package com.wzx.nirvana.repository;

import com.wzx.nirvana.model.User;

public interface UserRepository {

    User getById(String name);

    User getOne(String name);

    int update(User user);
}


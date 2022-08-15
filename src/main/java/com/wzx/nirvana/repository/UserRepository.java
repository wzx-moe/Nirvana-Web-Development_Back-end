package com.wzx.nirvana.repository;

import com.wzx.nirvana.model.User;

public interface UserRepository {

    User getOne(String name);

    int update(User user);
}


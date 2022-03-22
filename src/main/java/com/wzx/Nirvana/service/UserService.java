package com.wzx.Nirvana.service;

import com.wzx.Nirvana.mapper.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final String SESSION_KEY = "sessionId";

    @Autowired
    SignMapper signMapper;


}


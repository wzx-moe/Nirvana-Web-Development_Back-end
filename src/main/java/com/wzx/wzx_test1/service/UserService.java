package com.wzx.wzx_test1.service;

import com.wzx.wzx_test1.mapper.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final String SESSION_KEY = "sessionId";

    @Autowired
    SignMapper signMapper;


}


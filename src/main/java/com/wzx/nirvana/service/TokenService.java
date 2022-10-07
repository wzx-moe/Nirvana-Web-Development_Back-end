package com.wzx.nirvana.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wzx.nirvana.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author jinbin
 * @date 2018-07-08 21:04
 */
@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(user.getId())// 将 user id 保存到 token 里面
                .withExpiresAt(new Date(new Date().getTime() + 86400000))
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}

package com.wzx.nirvana.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Users")
public class User {

    @Id
    private String id;

    private String name;
    private String password;
    private boolean loginState;
    private String sessionId;
}

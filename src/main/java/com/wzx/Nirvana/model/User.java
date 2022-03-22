package com.wzx.Nirvana.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 954510354988569828L;
    private String id;
    private String name;
    private Date birthday;
    private String gender;
    private String phone;
    private String phone2;
    private String email;
    private String password;
    private boolean loginState;
    private boolean inService;
    private boolean admin;
    private String sessionId;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}

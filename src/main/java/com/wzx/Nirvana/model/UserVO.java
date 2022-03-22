package com.wzx.Nirvana.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 954510354988569828L;
    private String id;
    private String gender;
    private String phone;
    private String email;
    private String name;
}

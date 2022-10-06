package com.wzx.nirvana.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Contacts")
public class Contact {

    private String name;
    private String email;
    private String phone;
    private String message;
}

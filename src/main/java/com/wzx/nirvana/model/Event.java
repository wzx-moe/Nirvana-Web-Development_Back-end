package com.wzx.nirvana.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Events")
public class Event {

    @Id
    private String id;

    private String name;
    private String dateTime;
    private String duration;
    private String description;
    private String repeat;
    private String repeatCount;
}

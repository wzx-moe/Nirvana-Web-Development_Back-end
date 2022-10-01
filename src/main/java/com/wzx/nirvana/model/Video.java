package com.wzx.nirvana.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Videos")
public class Video {

    @Id
    private String id;

    private String name;

    private String description;

    private String url;
}

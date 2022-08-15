package com.wzx.nirvana.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Pages")
public class Page {

    @Id
    private String id;

    private String name;
    private String content;
}

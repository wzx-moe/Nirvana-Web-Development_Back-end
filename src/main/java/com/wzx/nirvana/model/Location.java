package com.wzx.nirvana.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Locations")
public class Location {

    private String longitude;
    private String latitude;
    private String url;
}

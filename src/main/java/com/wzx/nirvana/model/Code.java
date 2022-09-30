package com.wzx.nirvana.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Codes")
public class Code {

    private String code;
    private String[] url;
}

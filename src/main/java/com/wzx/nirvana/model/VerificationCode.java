package com.wzx.nirvana.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("VerificationCodes")
public class VerificationCode {

    private String verCode;
    private String sessionId;

}

package com.wzx.wzx_test1.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Review implements Serializable {

    private static final long serialVersionUID = 616232514660313443L;
    private String id;
    private String productID;
    private String userID;
    private String content;

}

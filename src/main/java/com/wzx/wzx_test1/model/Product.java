package com.wzx.wzx_test1.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 6561899463722822623L;
    private String id;
    private String name;
    private String description;
    private String price;
    private String category;
    private String picture;
    private String owner;

}

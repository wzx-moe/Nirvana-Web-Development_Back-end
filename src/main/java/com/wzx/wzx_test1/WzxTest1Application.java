package com.wzx.wzx_test1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wzx.wzx_test1.mapper")
public class WzxTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(WzxTest1Application.class, args);
    }

}


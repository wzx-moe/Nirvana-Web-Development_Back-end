package com.wzx.Nirvana;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wzx.Nirvana.mapper")
public class NirvanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NirvanaApplication.class, args);
    }

}


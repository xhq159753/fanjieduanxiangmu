package com.xie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xie.mapper")
public class ProvderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProvderApplication.class);
    }
}



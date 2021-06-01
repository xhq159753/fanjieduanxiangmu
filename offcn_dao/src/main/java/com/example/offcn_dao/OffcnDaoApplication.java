package com.example.offcn_dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class OffcnDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OffcnDaoApplication.class, args);
    }

}

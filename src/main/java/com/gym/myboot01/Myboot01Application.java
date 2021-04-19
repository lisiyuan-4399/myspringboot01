package com.gym.myboot01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gym.myboot01.mapper")
public class Myboot01Application {

    public static void main(String[] args) {

        SpringApplication.run(Myboot01Application.class, args);
    }

}

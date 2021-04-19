package com.gym.myboot01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot01 {

    @RequestMapping("/hello")
    public String hello(){
        return "你好，springboot" ;
    }
}

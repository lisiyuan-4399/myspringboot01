package com.gym.myboot01.controller;

import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/getUserInfo")
    public JsonResult getUserInfo(HttpServletRequest request){
        JsonResult userInfo = userService.getUserInfo(request);
        return userInfo ;
    }
}

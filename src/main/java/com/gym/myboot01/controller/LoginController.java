package com.gym.myboot01.controller;

import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.pojo.User;
import com.gym.myboot01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService ;

    @PostMapping("/toLogin/{getType}")
    public JsonResult toLogin(@RequestBody User user, @PathVariable("getType") String getType, HttpSession session){

        // 根据登录角色和账号密码进行登录
        JsonResult jsonResult = userService.toLogin(getType, user, session);

        return jsonResult ;
    }
}

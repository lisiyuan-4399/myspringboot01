package com.gym.myboot01.controller;

import com.gym.myboot01.pojo.*;
import com.gym.myboot01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService ;

    @PostMapping("/toLogin/{getType}")
    public JsonResult toLogin(@RequestBody User user,@PathVariable("getType") String getType){
        // 根据登录角色和账号密码进行登录
        JsonResult jsonResult = userService.toLogin(getType, user);
        return jsonResult ;
    }

    @PostMapping("/logout")
    public JsonResult logout(HttpServletRequest request){
        JsonResult jsonResult = new JsonResult() ;
        String token = request.getHeader("token");
        MyToken.token.remove(token);
        jsonResult.setMsg("退出成功");
        return jsonResult ;
    }
}

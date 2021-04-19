package com.gym.myboot01.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.pojo.User;

import javax.servlet.http.HttpSession;


public interface UserService extends IService<User> {


    JsonResult toLogin(String getType, User user , HttpSession session);
}

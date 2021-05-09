package com.gym.myboot01.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.pojo.PersonalDetailsVO;
import com.gym.myboot01.pojo.User;

import javax.servlet.http.HttpServletRequest;


public interface UserService extends IService<User> {

    // 登录方法
    JsonResult toLogin(String getType, User user);

    //获取用户信息
    JsonResult getUserInfo(HttpServletRequest request) ;

    //添加用户信息
    JsonResult addUser(User user);

    int updatePersonalDetails(PersonalDetailsVO detailsVO);

    PersonalDetailsVO getMyMessage(Integer id);
}

package com.gym.myboot01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.AdminMapper;
import com.gym.myboot01.mapper.CoachMapper;
import com.gym.myboot01.mapper.UserMapper;
import com.gym.myboot01.pojo.Admin;
import com.gym.myboot01.pojo.Coach;
import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.pojo.User;
import com.gym.myboot01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.transport.ObjectTable;

import javax.servlet.http.HttpSession;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CoachMapper coachMapper ;
    @Autowired
    private AdminMapper adminMapper ;


    @Override
    public JsonResult toLogin(String getType, User user, HttpSession session) {

        Object obj = null ;
        String sessionStr = getType ;
        JsonResult jsonResult = new JsonResult() ;
        String msg = "";


        if("user".equals(getType)){
            obj = userMapper.selectOne(getQueryWrapper(user));
        }else if("coach".equals(getType)){
            obj = coachMapper.selectOne(getQueryWrapper(user));
        }else if("admin".equals(getType)){
            obj = adminMapper.selectOne(getQueryWrapper(user));
        }
        if(obj != null){
            //登录成功
            session.setAttribute(sessionStr,obj);
            msg = "登录成功~!~" ;
        }else{
            //登录失败
            msg = "登录失败~!~" ;
            jsonResult.setCode("1");
        }
        jsonResult.setMsg(msg);
        return jsonResult ;
    }

    private QueryWrapper getQueryWrapper(User user){
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("username",user.getUsername()) ;
        queryWrapper.eq("password",user.getPassword()) ;
        return queryWrapper ;
    }
}

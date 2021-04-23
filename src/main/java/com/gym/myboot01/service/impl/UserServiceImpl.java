package com.gym.myboot01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.AdminMapper;
import com.gym.myboot01.mapper.CoachMapper;
import com.gym.myboot01.mapper.UserMapper;
import com.gym.myboot01.pojo.*;
import com.gym.myboot01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CoachMapper coachMapper ;
    @Autowired
    private AdminMapper adminMapper ;


    @Override
    public JsonResult toLogin(String getType, User user) {

        Object obj = null ;
        String sessionStr = getType ;
        JsonResult jsonResult = new JsonResult() ;
        String msg = "";
        Map<String,Object> map = new HashMap<>();

        if("user".equals(getType)){
            obj = userMapper.selectOne(getQueryWrapper(user));
        }else if("coach".equals(getType)){
            obj = coachMapper.selectOne(getQueryWrapper(user));
        }else if("admin".equals(getType)){
            obj = adminMapper.selectOne(getQueryWrapper(user));
        }
        if(obj != null){

            //生成 token 并返回给前端
            String token = UUID.randomUUID()+"";

            MyToken.token.put(token,obj) ;

            map.put("user",obj);
            map.put("token",token) ;
            jsonResult.setData(map);
            System.out.println("token:"+MyToken.token);
            msg = "登录成功~!~" ;
        }else{
            //登录失败
            msg = "登录失败~!~" ;
            jsonResult.setCode("1");
        }
        jsonResult.setMsg(msg);
        return jsonResult ;
    }

    @Override
    public JsonResult getUserInfo(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult() ;
        String token = request.getHeader("token");
        //根据 token 获取用户信息
        Object o = MyToken.token.get(token);
        jsonResult.setData(o);
        jsonResult.setMsg("获取用户信息成功");
        return jsonResult;
    }

    @Override
    public JsonResult addUser(User user) {
        JsonResult jsonResult = new JsonResult() ;
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("username",user.getUsername()) ;
        User one = userMapper.selectOne(queryWrapper);
        if(one != null){
            jsonResult.setMsg("用户已存在");
            jsonResult.setCode("1");
            return jsonResult ;
        }else{
            //用户不存在 ，进行添加操作
            userMapper.insert(user);
            jsonResult.setMsg("添加成功");
            jsonResult.setData(user);
            return jsonResult ;
        }
    }

    private QueryWrapper getQueryWrapper(User user){
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("username",user.getUsername()) ;
        queryWrapper.eq("password",user.getPassword()) ;
        return queryWrapper ;
    }
}

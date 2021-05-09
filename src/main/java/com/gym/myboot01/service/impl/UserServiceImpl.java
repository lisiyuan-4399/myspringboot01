package com.gym.myboot01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.AdminMapper;
import com.gym.myboot01.mapper.CoachMapper;
import com.gym.myboot01.mapper.UserMapper;
import com.gym.myboot01.mapper.UserMessageMapper;
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
    @Autowired
    private UserMessageMapper userMessageMapper ;


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

    //修改个人信息
    @Override
    public int updatePersonalDetails(PersonalDetailsVO detailsVO) {
        //判断是否修改 user 表中的信(全部都改不就完了)
        User user = new User() ;
        user.setId(detailsVO.getUid());
        user.setName(detailsVO.getName());
        user.setSex(detailsVO.getSex());
        user.setPhone(detailsVO.getPhone());
        user.setEmail(detailsVO.getEmail());
        int i = userMapper.updateById(user);
        UserMessage userMessage = new UserMessage() ;
        userMessage.setId(detailsVO.getId());
        userMessage.setAddress(detailsVO.getAddress());
        userMessage.setBirthday(detailsVO.getBirthday());
        userMessage.setHeight(detailsVO.getHeight());
        userMessage.setDescription(detailsVO.getDescription());
        userMessage.setQQ(detailsVO.getQQ());
        int i1 = userMessageMapper.updateById(userMessage);
        return (i>0 && i1>0) ? 1:0;
    }

    //获取个人 Message
    @Override
    public PersonalDetailsVO getMyMessage(Integer id) {

        return  userMessageMapper.getMyMessage(id) ;
    }

    private QueryWrapper getQueryWrapper(User user){
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("username",user.getUsername()) ;
        queryWrapper.eq("password",user.getPassword()) ;
        return queryWrapper ;
    }

    private Boolean detailsExistUser(PersonalDetailsVO detailsVO){
        return (detailsVO.getName() != null || detailsVO.getSex()!=null || detailsVO.getEmail()!=null || detailsVO.getPhone()!=null);
    }
}

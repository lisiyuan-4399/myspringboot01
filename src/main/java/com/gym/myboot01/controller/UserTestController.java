package com.gym.myboot01.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.myboot01.pojo.User;
import com.gym.myboot01.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/user1")
public class UserTestController {

    @Autowired
    private UserTestService userService ;

    @PostMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @PostMapping("/insertUser")
    public Integer insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }

    @PostMapping("/updateUser")
    public Integer updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping("/selectUserById")
    public User selectUserById(@RequestParam("id") Integer id){
        return userService.selectUserById(id);
    }

    @PostMapping("/selectByMap")
    public List<User> selectByMap(@RequestBody User user){
        Map<String,Object> map = new HashMap<>() ;
        map.put("username",user.getUsername()) ;
        map.put("password",user.getPassword()) ;
        return userService.selectByMap(map);
    }

    @PostMapping("/selectBatchIds")
    public List<User> selectBatchIds(@RequestBody List<Integer> ids){
        return userService.selectBatchIds(ids);
    }

    @PostMapping("/selectPage")
    public List<User> selectPage(@RequestParam("pageCurrent") Integer pageCurrent,
                                 @RequestParam("pageSize") Integer pageSize){
        Page<User> page = new Page<>() ;
        page.setCurrent(pageCurrent);
        page.setSize(pageSize) ;
        return userService.selectPage(page);
    }

    @GetMapping("/deleteUserById")
    public Integer deleteUserById(@RequestParam("id") Integer id){
        return userService.deleteUserById(id);
    }

    @PostMapping("/selectPageCondition")
    public List selectPageCondition(@RequestParam("pageCurrent") Integer pageCurrent,
                                    @RequestParam("pageSize") Integer pageSize){
        Page<User> page = new Page<>() ;
        page.setCurrent(pageCurrent);
        page.setSize(pageSize) ;
        QueryWrapper<User> wrapper = new QueryWrapper<>() ;
        wrapper.like("username","lo") ;
        return userService.selectPageCondition(page,wrapper);
    }
}

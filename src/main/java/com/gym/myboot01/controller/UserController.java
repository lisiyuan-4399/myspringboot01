package com.gym.myboot01.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.pojo.PersonalDetailsVO;
import com.gym.myboot01.pojo.User;
import com.gym.myboot01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping("/getUserAll")
    public JsonResult getUserAll(){
        JsonResult<List<User>> jsonResult = new JsonResult<>() ;
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("is_delete",0) ;
        List<User> list = userService.list(queryWrapper);
        jsonResult.setData(list);
        jsonResult.setMsg("查询全部用户成功");
        return jsonResult ;
    }

    @PostMapping("/addUser")
    public JsonResult addUser(@RequestBody User user){
        JsonResult jsonResult = userService.addUser(user) ;
        return jsonResult ;
    }

    @PostMapping("/updateUser")
    public JsonResult updateUser(@RequestBody User user){
        JsonResult jsonResult = new JsonResult() ;
        boolean b = userService.updateById(user);
        if(b){
            jsonResult.setMsg("修改信息成功");
            return jsonResult ;
        }
        jsonResult.setMsg("修改信息失败");
        jsonResult.setCode("1");
        return jsonResult ;
    }

    @PostMapping("/deleteUser")
    public JsonResult deleteUser(@RequestParam("id") Integer id){
        JsonResult jsonResult = new JsonResult() ;
        User user = userService.getById(id);
        if(user != null){
            user.setIs_delete(1);
            boolean b = userService.updateById(user);
            if(b){
                jsonResult.setMsg("删除用户信息成功");
                return jsonResult ;
            }
        }
        jsonResult.setMsg("删除用户信息失败");
        jsonResult.setCode("1");
        return jsonResult ;
    }

    //获取个人信息
    @RequestMapping("getMyMessage")
    public JsonResult getMyMessage(@RequestParam("userId") Integer id){
        JsonResult jsonResult = new JsonResult() ;
        PersonalDetailsVO personalDetailsVO = userService.getMyMessage(id) ;
        jsonResult.setMsg("查询个人信息成功!");
        jsonResult.setData(personalDetailsVO);
        return jsonResult ;
    }

    //修改个人信息
    @RequestMapping("/updateMyMessage")
    public JsonResult updateMyMessage(@RequestBody PersonalDetailsVO detailsVO){
        JsonResult jsonResult = new JsonResult() ;
        int i = userService.updatePersonalDetails(detailsVO) ;
        if (i>0){
            jsonResult.setMsg("修改个人信息成功!");
        }else{
            jsonResult.setMsg("修改个人信息失败!");
            jsonResult.setCode("1");
        }
        return jsonResult ;
    }
}

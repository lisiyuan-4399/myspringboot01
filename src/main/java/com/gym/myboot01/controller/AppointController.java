package com.gym.myboot01.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.myboot01.pojo.Appoint;
import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.service.AppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/appoint")
public class AppointController {

    @Autowired
    private AppointService appointService ;

    // 用户进行预约
    @RequestMapping("/addAppointByUser")
    public JsonResult addAppointByUser(@RequestParam("userId") Integer userId,@RequestParam("coachId")Integer coachId){
        JsonResult jsonResult = new JsonResult() ;
        Boolean b = appointService.addAppointByUser(userId,coachId) ;
        if(b){
            jsonResult.setMsg("添加预约成功");
        }else{
            jsonResult.setCode("1");
            jsonResult.setMsg("添加预约失败");
        }
        return jsonResult ;
    }

    //获取我的全部预约
    @RequestMapping("/getAppointAll")
    public JsonResult getAppointAll(@RequestParam("userId") Integer userId){
        JsonResult jsonResult = new JsonResult() ;
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("u_id",userId) ;
        List<Appoint> list = appointService.list(queryWrapper);
        jsonResult.setMsg("查询全部预约成功");
        jsonResult.setData(list);
        return jsonResult ;
    }
}

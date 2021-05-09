package com.gym.myboot01.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.myboot01.pojo.Appoint;
import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.pojo.UserMyAppointVO;
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
        //使用连接查询
        List<UserMyAppointVO> list = appointService.getAppointAll(userId);
        jsonResult.setMsg("查询全部预约成功");
        jsonResult.setData(list);
        return jsonResult ;
    }

    //获取我的全部预约
    @RequestMapping("/getCoachAppointAll")
    public JsonResult getCoachAppointAll(@RequestParam("coachId") Integer coachId){
        JsonResult jsonResult = new JsonResult() ;
        //使用连接查询
        List<UserMyAppointVO> list = appointService.getCoachAppointAll(coachId);
        jsonResult.setMsg("查询全部预约成功(预约)");
        jsonResult.setData(list);
        return jsonResult ;
    }

    //取消预约
    @RequestMapping("/cancleAppointById")
    public JsonResult cancleAppointById(@RequestParam("id") Integer id){
        JsonResult jsonResult = new JsonResult() ;

        boolean b = appointService.cancleAppointById(id);
        if (b){
            jsonResult.setMsg("取消预约成功!");
        }else{
            jsonResult.setMsg("取消预约失败!");
            jsonResult.setCode("1");
        }
        return jsonResult ;
    }

    //完成预约
    @RequestMapping("/completeAppoit")
    public JsonResult completeAppoit(@RequestParam("id") Integer id){
        JsonResult jsonResult = new JsonResult() ;
        appointService.completeAppoit(id) ;
        jsonResult.setMsg("完成预约成功");
        return jsonResult ;
    }

    //评价预约
    @RequestMapping("/evaluateAppoit")
    public JsonResult evaluateAppoit(@RequestBody Appoint appoint){
        JsonResult jsonResult = new JsonResult() ;
        boolean b = appointService.updateById(appoint);
        if(b){
            jsonResult.setMsg("评价预约成功");
        }else{
            jsonResult.setMsg("评价预约失败");
            jsonResult.setCode("1");
        }
        return jsonResult ;
    }
}

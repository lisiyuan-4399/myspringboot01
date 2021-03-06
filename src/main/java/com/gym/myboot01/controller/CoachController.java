package com.gym.myboot01.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.myboot01.pojo.Coach;
import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/coach")
public class CoachController {



    @Autowired
    private CoachService coachService ;

    @RequestMapping("/getCoachAll")
    public JsonResult getCoachAll(){
        JsonResult<List<Coach>> jsonResult = new JsonResult<>() ;
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("is_delete",0) ;
        List<Coach> list = coachService.list(queryWrapper);
        jsonResult.setData(list);
        jsonResult.setMsg("查询全部教练成功");
        return jsonResult ;
    }

    @PostMapping("/addCoach")
    public JsonResult addCoach(@RequestBody Coach coach){
        JsonResult jsonResult = coachService.addUser(coach) ;
        return jsonResult ;
    }

    @PostMapping("/updateCoach")
    public JsonResult updateCoach(@RequestBody Coach coach){
        JsonResult jsonResult = new JsonResult() ;
        boolean b = coachService.updateById(coach);
        if(b){
            jsonResult.setMsg("修改教练信息成功");
            return jsonResult ;
        }
        jsonResult.setMsg("修改教练信息失败");
        jsonResult.setCode("1");
        return jsonResult ;
    }

    @PostMapping("/deleteCoach")
    public JsonResult deleteCoach(@RequestParam("id") Integer id){
        JsonResult jsonResult = new JsonResult() ;
        Coach coach = coachService.getById(id);
        if(coach != null){
            coach.setIs_delete(1);
            boolean b = coachService.updateById(coach);
            if(b){
                jsonResult.setMsg("删除教练信息成功");
                return jsonResult ;
            }
        }
        jsonResult.setMsg("删除教练信息失败");
        jsonResult.setCode("1");
        return jsonResult ;
    }


    @RequestMapping( value = "/uploadPath",method = RequestMethod.POST,consumes = "multipart/form-data")
    public JsonResult uploadPath(MultipartFile file,Coach coach) throws IOException {
        System.out.println(file);
        System.out.println(coach.toString());
        JsonResult jsonResult = new JsonResult() ;
        Integer integer = coachService.uploadPath(file, coach);
        if(integer>0){
            jsonResult.setMsg("添加教练成功");
        }else {
            jsonResult.setMsg("添加教练失败");
            jsonResult.setCode("1");
        }
        return jsonResult ;
    }

    @RequestMapping( value = "/updateUploadPath",method = RequestMethod.POST,consumes = "multipart/form-data")
    public JsonResult updateUploadPath(MultipartFile file,Coach coach) throws IOException {
        System.out.println(file);
        System.out.println(coach.toString());
        JsonResult jsonResult = new JsonResult() ;
        Integer i = coachService.updateUploadPath(file,coach) ;
        if(i>0){
            jsonResult.setMsg("修改教练成功");
        }else {
            jsonResult.setMsg("修改教练失败");
            jsonResult.setCode("1");
        }
        return jsonResult;
    }

    @RequestMapping("/getCoachAllByConditions")
    public JsonResult getCoachAllByConditions(){
        JsonResult<List<Coach>> jsonResult = new JsonResult<>() ;
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("is_delete",0) ;
        queryWrapper.eq("is_inuse",0) ;
        queryWrapper.select("id","name","age","num","sex","phone","type","email","description","pic");
        List<Coach> list = coachService.list(queryWrapper);
        jsonResult.setData(list);
        jsonResult.setMsg("查询全部教练成功");
        return jsonResult ;
    }

    //获取全部教练名称和预约次数
    @RequestMapping("/getCoachAllNameAndNum")
    public JsonResult getCoachAllNameAndNum(){

        JsonResult jsonResult = new JsonResult() ;
        List<Coach> list = coachService.list();
        Map coachMap = new HashMap() ;
        List nameList = new ArrayList() ;
        List numList = new ArrayList() ;
        if(list!=null){
            for (int i=0 ; i<list.size() ; i++){
                nameList.add(list.get(i).getName()) ;
            }
            coachMap.put("name",nameList) ;
            coachMap.put("num",numList) ;
        }
        jsonResult.setData(coachMap);
        return jsonResult ;
    }

}

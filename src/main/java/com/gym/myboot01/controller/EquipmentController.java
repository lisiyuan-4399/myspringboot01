package com.gym.myboot01.controller;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.myboot01.pojo.Coach;
import com.gym.myboot01.pojo.Equipment;
import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService ;

    @RequestMapping("/getEquipmentAll")
    public JsonResult getEquipmentAll(){
        JsonResult<List<Equipment>> jsonResult = new JsonResult<>() ;
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("is_delete",0) ;
        List<Equipment> list = equipmentService.list(queryWrapper);
        System.out.println(list.toString());
        jsonResult.setData(list);
        jsonResult.setMsg("查询全部健身器材成功");
        return jsonResult ;
    }

    @RequestMapping("/updateEquipment")
    public JsonResult updateEquipment(@RequestBody Equipment equipment){
        JsonResult jsonResult = new JsonResult() ;
        boolean b = equipmentService.updateById(equipment);
        if(b){
            jsonResult.setMsg("修改器材信息成功");
            return jsonResult ;
        }
        jsonResult.setMsg("修改器材信息失败");
        jsonResult.setCode("1");
        return jsonResult ;
    }

    @RequestMapping( value = "/uploadPath",method = RequestMethod.POST,consumes = "multipart/form-data")
    public JsonResult uploadPath(MultipartFile file, Equipment equipment) throws IOException {
        System.out.println(file);
        System.out.println(equipment.toString());
        JsonResult jsonResult = new JsonResult() ;
        Integer integer = equipmentService.uploadPath(file, equipment);
        if(integer>0){
            jsonResult.setMsg("添加器材成功");
        }else {
            jsonResult.setMsg("添加器材失败");
            jsonResult.setCode("1");
        }
        return jsonResult ;
    }

    @RequestMapping( value = "/updateUploadPath",method = RequestMethod.POST,consumes = "multipart/form-data")
    public JsonResult updateUploadPath(MultipartFile file,Equipment equipment) throws IOException {
        System.out.println(file);
        System.out.println(equipment.toString());
        JsonResult jsonResult = new JsonResult() ;
        Integer i = equipmentService.updateUploadPath(file,equipment) ;
        if(i>0){
            jsonResult.setMsg("修改器材成功");
        }else {
            jsonResult.setMsg("修改器材失败");
            jsonResult.setCode("1");
        }
        return jsonResult;
    }

    @PostMapping("/deleteEquipment")
    public JsonResult deleteEquipment(@RequestParam("id") Integer id){
        JsonResult jsonResult = new JsonResult() ;
        Equipment equipment = equipmentService.getById(id);
        if(equipment != null){
            equipment.setIs_delete(1);
            boolean b = equipmentService.updateById(equipment);
            if(b){
                jsonResult.setMsg("删除器材信息成功");
                return jsonResult ;
            }
        }
        jsonResult.setMsg("删除器材信息失败");
        jsonResult.setCode("1");
        return jsonResult ;
    }

    @PostMapping("/getEquipmentPic")
    public JsonResult<List<Equipment>> getEquipmentPic(){
        JsonResult jsonResult = new JsonResult() ;
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>() ;
        queryWrapper.select("pic");
        List<Equipment> list = equipmentService.list(queryWrapper);
        jsonResult.setData(list);
        jsonResult.setMsg("获取器材图片成功");
        return jsonResult ;
    }
}

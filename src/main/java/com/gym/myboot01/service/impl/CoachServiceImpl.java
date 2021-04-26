package com.gym.myboot01.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.CoachMapper;
import com.gym.myboot01.pojo.Coach;
import com.gym.myboot01.pojo.JsonResult;
import com.gym.myboot01.pojo.User;
import com.gym.myboot01.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
public class CoachServiceImpl extends ServiceImpl<CoachMapper,Coach> implements CoachService {

    @Autowired
    private CoachMapper coachMapper;

    @Value("${photo.dir}")
    private String localPath ;

    @Override
    public JsonResult addUser(Coach coach) {
        JsonResult jsonResult = new JsonResult() ;
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("username",coach.getUsername()) ;
        Coach one = coachMapper.selectOne(queryWrapper);
        if(one != null){
            jsonResult.setMsg("该教练已存在");
            jsonResult.setCode("1");
            return jsonResult ;
        }else{
            //用户不存在 ，进行添加操作
            coachMapper.insert(coach);
            jsonResult.setMsg("添加成功");
            jsonResult.setData(coach);
            return jsonResult ;
        }
    }

    @Override
    public Integer uploadPath(MultipartFile file, Coach coach) throws IOException {
        //保存数据库的路径
        String sqlPath = null;
        //定义 文件名
        String filename=null;
        if(!file.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType(); //image/jpeg
            //获得文件后缀名
            String suffixName=contentType.substring(contentType.indexOf("/")+1); //jpeg
            // 得到 文件名
            filename=uuid+"."+suffixName;
            // 文件保存路径(保存文件路径)
            file.transferTo(new File(localPath+filename));
            //把图片的相对路径保存至数据库
            sqlPath = "/images/"+filename;
            coach.setPic(sqlPath);
        }
        JsonResult i = this.addUser(coach) ;

        return "0".equals(i.getCode()) ? 1 : 0 ;
    }

    @Override
    public Integer updateUploadPath(MultipartFile file, Coach coach) throws IOException {
        //保存数据库的路径
        String sqlPath = null;
        //定义 文件名
        String filename=null;
        if(!file.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType(); //image/jpeg
            //获得文件后缀名
            String suffixName=contentType.substring(contentType.indexOf("/")+1); //jpeg
            // 得到 文件名
            filename=uuid+"."+suffixName;
            // 文件保存路径(保存文件路径)
            file.transferTo(new File(localPath+filename));
            //把图片的相对路径保存至数据库
            sqlPath = "/images/"+filename;
            coach.setPic(sqlPath);
        }
        int i = coachMapper.updateById(coach);
        return i;
    }
}

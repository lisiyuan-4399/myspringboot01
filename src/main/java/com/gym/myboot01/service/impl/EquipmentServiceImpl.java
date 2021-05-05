package com.gym.myboot01.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.EquipmentMapper;
import com.gym.myboot01.pojo.Equipment;
import com.gym.myboot01.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;


@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper,Equipment> implements EquipmentService {


    @Value("${photo.equipment}")
    private String localPath ;

    @Autowired
    private EquipmentMapper equipmentMapper ;

    @Override
    public Integer uploadPath(MultipartFile file, Equipment equipment) throws IOException {
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
            equipment.setPic(sqlPath);
        }
        equipment.setBeginTime(new Date());
        int insert = equipmentMapper.insert(equipment);

        return insert;
    }

    @Override
    public Integer updateUploadPath(MultipartFile file, Equipment equipment) throws IOException {
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
            equipment.setPic(sqlPath);
        }
        int i = equipmentMapper.updateById(equipment);
        return i;
    }
}

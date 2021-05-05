package com.gym.myboot01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.myboot01.pojo.Equipment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EquipmentService extends IService<Equipment> {

    Integer uploadPath(MultipartFile file, Equipment equipment) throws IOException;

    Integer updateUploadPath(MultipartFile file, Equipment equipment) throws IOException;
}

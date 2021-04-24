package com.gym.myboot01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.myboot01.pojo.Coach;
import com.gym.myboot01.pojo.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CoachService extends IService<Coach> {

    JsonResult addUser(Coach coach);

    Integer uploadPath(MultipartFile file, Coach coach) throws IOException;
}

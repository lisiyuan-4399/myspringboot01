package com.gym.myboot01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.myboot01.pojo.Appoint;

public interface AppointService extends IService<Appoint> {

    Boolean addAppointByUser(Integer userId, Integer coachId);
}

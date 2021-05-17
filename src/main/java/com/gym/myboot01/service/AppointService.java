package com.gym.myboot01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.myboot01.pojo.Appoint;
import com.gym.myboot01.pojo.UserMyAppointVO;

import java.util.List;

public interface AppointService extends IService<Appoint> {

    Boolean addAppointByUser(Integer userId, Integer coachId,Integer sex , Integer type);

    List<UserMyAppointVO> getAppointAll(Integer userId);

    boolean cancleAppointById(Integer id);

    List getCoachAppointAll(Integer coachId);

    //根据 id 完成预约
    void completeAppoit(Integer id);

    Boolean addAutoAppoint(Integer userId);
}

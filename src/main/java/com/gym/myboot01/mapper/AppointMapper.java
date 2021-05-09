package com.gym.myboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.myboot01.pojo.Appoint;
import com.gym.myboot01.pojo.UserMyAppointVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AppointMapper extends BaseMapper<Appoint> {

    List<UserMyAppointVO> getAppointAll(@Param("userId") Integer userId);

    List<UserMyAppointVO> getCoachAppointAll(@Param("coachId") Integer coachId);
}

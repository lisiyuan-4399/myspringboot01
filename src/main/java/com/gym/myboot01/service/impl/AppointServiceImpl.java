package com.gym.myboot01.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.AppointMapper;
import com.gym.myboot01.mapper.CoachMapper;
import com.gym.myboot01.pojo.Appoint;
import com.gym.myboot01.pojo.Coach;
import com.gym.myboot01.service.AppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppointServiceImpl extends ServiceImpl<AppointMapper,Appoint> implements AppointService {

    @Autowired
    private AppointMapper appointMapper;
    @Autowired
    private CoachMapper coachMapper ;

    //添加预约
    @Override
    public Boolean addAppointByUser(Integer userId, Integer coachId) {

        Boolean b = false ;
        Appoint appoint = new Appoint(userId,coachId) ;
        int insert = appointMapper.insert(appoint);
        if(insert>0){
            b = true ;
            Coach coach = new Coach(coachId,1) ;
            coachMapper.updateById(coach) ;
        }

        return b;
    }
}

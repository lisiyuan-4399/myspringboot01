package com.gym.myboot01.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.AppointMapper;
import com.gym.myboot01.mapper.CoachMapper;
import com.gym.myboot01.pojo.Appoint;
import com.gym.myboot01.pojo.Coach;
import com.gym.myboot01.pojo.UserMyAppointVO;
import com.gym.myboot01.service.AppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


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

    @Override
    public List<UserMyAppointVO> getAppointAll(Integer userId) {
        return appointMapper.getAppointAll(userId);
    }

    @Override
    public boolean cancleAppointById(Integer id) {
        //查询预约信息
        Appoint appoint = appointMapper.selectById(id);
        //删除用户
        int i = appointMapper.deleteById(id);
        //将教练预约状态进行修改0
        if(i>0){
            Coach coach = new Coach() ;
            coach.setId(appoint.getCId());
            coach.setIs_inuse(0);
            coachMapper.updateById(coach);
            return true ;
        }
        return false;
    }

    @Override
    public List getCoachAppointAll(Integer coachId) {
        return appointMapper.getCoachAppointAll(coachId);
    }

    //根据 id  完成预约
    @Override
    public void completeAppoit(Integer id) {
        //将此 id 的 is_valid 属性改为 1 (将预约属性改为完成)
        Appoint appoint = appointMapper.selectById(id);
        appoint.setIsValid(1);
        appoint.setEndTime(new Date());
        int i = appointMapper.updateById(appoint);
        if(i>0){
            //将此教练的预约次数加一并将此教练变为可预约状态
            Coach coach = coachMapper.selectById(appoint.getCId());
            Integer num = coach.getNum() + 1;
            coach.setNum(num);
            coach.setIs_inuse(0);
            coachMapper.updateById(coach);
        }
    }
}

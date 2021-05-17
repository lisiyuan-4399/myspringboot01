package com.gym.myboot01.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.AppointMapper;
import com.gym.myboot01.mapper.CoachMapper;
import com.gym.myboot01.pojo.Appoint;
import com.gym.myboot01.pojo.Coach;
import com.gym.myboot01.pojo.UserMyAppointVO;
import com.gym.myboot01.service.AppointService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class AppointServiceImpl extends ServiceImpl<AppointMapper,Appoint> implements AppointService {

    @Autowired
    private AppointMapper appointMapper;
    @Autowired
    private CoachMapper coachMapper ;

    //添加预约
    @Override
    public Boolean addAppointByUser(Integer userId, Integer coachId,Integer sex ,Integer type) {

        Boolean b = false ;
        Integer insert = 0 ;
        Appoint appoint = null ;
        if(sex != null || type != null){
            QueryWrapper queryWrapper = new QueryWrapper() ;
            if(sex != null){
                queryWrapper.eq("sex",sex) ;
            }
            if(type != null){
                queryWrapper.eq("type",type) ;
            }
            queryWrapper.eq("is_inuse",0) ;
            List list = coachMapper.selectList(queryWrapper);
            if(list.size()>0){
                Random random = new Random() ;
                int nextInt = random.nextInt(list.size());
                Coach coach = (Coach) list.get(nextInt);
                coachId = coach.getId() ;
            }
        }
        if(coachId != null){
            appoint = new Appoint(userId,coachId) ;
            insert = appointMapper.insert(appoint);
        }
        // 当完成预约的时候 ， 将教练的已预约状态变为 1
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

    //添加自动预约（上一次预约教练）
    @Override
    public Boolean addAutoAppoint(Integer userId){
        Boolean b = false ;
        QueryWrapper queryWrapper = new QueryWrapper() ;
        queryWrapper.eq("u_id",userId) ;
        queryWrapper.eq("is_valid",1) ;
        List<Appoint> list = appointMapper.selectList(queryWrapper);
        //获取最近一条预约记录
        if(list.size()>0){
            Appoint appoint = list.get(list.size()-1) ;
            //查询该教练是否已经被预约
            queryWrapper = new QueryWrapper() ;
            queryWrapper.eq("is_inuse",0) ;
            queryWrapper.eq("id",appoint.getCId()) ;
            Coach coach = coachMapper.selectOne(queryWrapper);
            if(coach != null){
                //进行预约操作
                appoint.setIsValid(0);
                appoint.setEndTime(null);
                appoint.setEvaluate("-");
                appointMapper.insert(appoint);
                coach.setIs_inuse(1);
                coachMapper.updateById(coach) ;
                b = true ;
            }
        }

        return b ;
    }
    //添加自动预约（根据统计爱好进行预约）
//    @Override
//    public Boolean addAutoAppoint(Integer userId) {
//        Boolean b = false ;
//        //1.根据 id 对之前预约的教练进行查询，并且返回list
//        QueryWrapper queryWrapper = new QueryWrapper() ;
//        queryWrapper.eq("u_id",userId) ;
//        queryWrapper.eq("is_valid",1) ;
//        List<Appoint> list = appointMapper.selectList(queryWrapper);
//        List<Integer> coachIds = list.stream().map(Appoint::getCId).collect(Collectors.toList());
//        System.out.println(coachIds);
//        Map map = new HashMap<Integer,Integer>() ;
//        for (Integer i:coachIds) {
//            //判断 map 是否存在该 id 教练 ，存在进行 ++ ， 不存在进行添加
//            if(map.containsKey(i)){
//                Integer integer = (Integer) map.get(i);
//                map.put(i, integer+1);
//            }else{
//                map.put(i,1) ;
//            }
//        }
//        //存入的值
//        List l = new ArrayList() ;
//        Set set = map.keySet();
//        for (Object o:set) {
//            l.add(map.get(o)) ;
//        }
//        System.out.println(l);
//        Collections.sort(l);
//        System.out.println(l);
//
//        return b;
//    }
}

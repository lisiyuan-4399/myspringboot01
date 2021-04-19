package com.gym.myboot01.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.CoachMapper;
import com.gym.myboot01.pojo.Coach;
import com.gym.myboot01.service.CoachService;
import org.springframework.stereotype.Service;


@Service
public class CoachServiceImpl extends ServiceImpl<CoachMapper,Coach> implements CoachService {


}

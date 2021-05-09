package com.gym.myboot01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.UserMessageMapper;
import com.gym.myboot01.pojo.UserMessage;
import com.gym.myboot01.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper,UserMessage> implements UserMessageService {

    @Autowired
    private UserMessageMapper userMessageMapper;



}

package com.gym.myboot01.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.AdminMapper;

import com.gym.myboot01.pojo.Admin;
import com.gym.myboot01.service.AdminService;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {


}

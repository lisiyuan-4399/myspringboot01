package com.gym.myboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.myboot01.pojo.User;

import java.util.List;

public interface UserTestMapper extends BaseMapper<User> {

    public List<User> findAllUser() ;


}

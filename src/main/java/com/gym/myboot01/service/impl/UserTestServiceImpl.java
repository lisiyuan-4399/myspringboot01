package com.gym.myboot01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.myboot01.mapper.UserTestMapper;
import com.gym.myboot01.pojo.User;
import com.gym.myboot01.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper,User> implements UserTestService {

    @Autowired
    private UserTestMapper userTestMapper;

    @Override
    public List<User> findAllUser() {
        return userTestMapper.findAllUser();
    }

    @Override
    public int insertUser(User user) {
        int insert = userTestMapper.insert(user);
        System.out.println("添加:"+insert);
        //返回用户主键
        return user.getId();
    }

    @Override
    public int updateUser(User user) {
        return userTestMapper.updateById(user);
    }

    @Override
    public User selectUserById(Integer id) {
        return userTestMapper.selectById(id);
    }

    @Override
    public List<User> selectByMap(Map<String, Object> map) {

        return userTestMapper.selectByMap(map);
    }

    @Override
    public List<User> selectBatchIds(List<Integer> idList) {

        return userTestMapper.selectBatchIds(idList);
    }

    @Override
    public List<User>  selectPage(Page<User> page) {
        IPage userIPage = userTestMapper.selectPage(page, null);
        List<User> users = userIPage.getRecords();
        return users;
    }

    @Override
    public Integer deleteUserById(Integer id) {

        return userTestMapper.deleteById(id);
    }

    @Override
    public List<User> selectPageCondition(Page<User> page, QueryWrapper wrapper) {
        IPage iPage = userTestMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }


}

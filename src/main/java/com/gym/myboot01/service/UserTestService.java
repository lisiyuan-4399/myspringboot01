package com.gym.myboot01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.myboot01.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserTestService extends IService<User> {

    //查询全部用户
    public List<User> findAllUser();

    //添加用户
    int insertUser(User user);

    //更改用户
    int updateUser(User user) ;

    //根据 id 查询用户
    User selectUserById(Integer id) ;

    //根据条件查询一条数据
//    User selectUserByCondition() ;

    //根据查询条件返回多条数据
    List<User> selectByMap(Map<String,Object> map) ;

    //通过 id 批量查询
    List<User> selectBatchIds(List<Integer> idList) ;

    //分页查询
    List<User> selectPage(Page<User> page) ;

    //删除操作
    Integer deleteUserById(Integer id) ;

    //根据条件删除

    //根据 id 批量删除

    //带条件的分页
    List selectPageCondition(Page<User> page, QueryWrapper wrapper) ;
}

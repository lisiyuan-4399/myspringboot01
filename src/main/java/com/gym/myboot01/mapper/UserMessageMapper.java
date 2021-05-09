package com.gym.myboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.myboot01.pojo.PersonalDetailsVO;
import com.gym.myboot01.pojo.UserMessage;
import org.apache.ibatis.annotations.Param;


public interface UserMessageMapper extends BaseMapper<UserMessage> {

    PersonalDetailsVO getMyMessage(@Param("id") Integer id);
}

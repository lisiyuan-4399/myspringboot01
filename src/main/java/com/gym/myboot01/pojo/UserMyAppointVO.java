package com.gym.myboot01.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserMyAppointVO {

    private Integer id ;

    //教练名称
    private String name ;

    private Integer sex ;

    private Integer age ;

    private String phone ;

    private Date beginTime ;

    private Date endTime ;

    private String evaluate ;

    private Integer isValid ;

}

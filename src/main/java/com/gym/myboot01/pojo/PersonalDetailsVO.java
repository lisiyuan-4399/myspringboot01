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
public class PersonalDetailsVO {

    private Integer id ;

    private Integer uid ;

    private String name ;

    private Integer sex ;

    private String phone ;

    private String email ;

    private String description ;

    private String address ;

    private Double height ;

    private String QQ ;

    private Date birthday ;
}

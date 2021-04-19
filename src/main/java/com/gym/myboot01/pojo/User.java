package com.gym.myboot01.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
public class User {

    //指定自增策略
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name ;

    @TableField(value = "sex")
    private Integer sex ;

    @TableField(value = "username")
    private String username ;

    @TableField(value = "password")
    private String password ;

    @TableField(value = "email")
    private String email ;

    @TableField(value = "phone")
    private String phone ;

    @TableField(value = "is_delete")
    private String is_delete ;
}

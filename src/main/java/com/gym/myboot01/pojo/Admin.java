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
@TableName(value = "t_admin")
public class Admin {

    //指定自增策略
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name ;

    @TableField(value = "username")
    private String username ;

    @TableField(value = "password")
    private String password ;


    @TableField(value = "phone")
    private String phone ;

    @TableField(value = "is_delete")
    private Integer is_delete ;
}

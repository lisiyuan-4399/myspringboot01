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
@TableName(value = "t_coach")
public class Coach {

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

    @TableField(value = "type")
    private Integer type ;

    @TableField(value = "age")
    private Integer age ;

    @TableField(value = "description")
    private String description ;

    //图片
    @TableField(value = "pic")
    private String pic ;

    //预约次数
    @TableField(value = "num")
    private Integer num ;

    //是否被预约
    @TableField(value = "is_inuse")
    private Integer is_inuse ;

    @TableField(value = "is_delete")
    private Integer is_delete ;

    public Coach(Integer id, Integer is_inuse) {
        this.id = id;
        this.is_inuse = is_inuse;
    }
}

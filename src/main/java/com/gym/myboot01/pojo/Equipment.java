package com.gym.myboot01.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_equip")
public class Equipment {

    //指定自增策略
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name ;


    @TableField(value = "description")
    private String description ;

    //图片
    @TableField(value = "pic")
    private String pic ;

    //图片
    @TableField(value = "begintime")
    private Date beginTime ;

    //是否被预约
    @TableField(value = "is_inuse")
    private Integer is_inuse ;

    @TableField(value = "is_delete")
    private Integer is_delete ;
}

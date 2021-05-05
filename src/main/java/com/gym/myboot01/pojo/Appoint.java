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
@TableName(value = "t_appoint")
public class Appoint {

    //指定自增策略
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "u_id")
    private Integer uId ;

    @TableField(value = "c_id")
    private Integer cId ;

    @TableField(value = "begintime")
    private Date beginTime ;


    @TableField(value = "endtime")
    private Date endTime ;

    @TableField(value = "evaluate")
    private String evaluate ;

    @TableField(value = "is_valid")
    private Integer isValid ;

    public Appoint(Integer uId, Integer cId) {
        this.uId = uId;
        this.cId = cId;
        this.beginTime = new Date();
    }
}
